[CmdletBinding(SupportsShouldProcess = $true)]
param()

$repoRoot = (& git -C $PSScriptRoot rev-parse --show-toplevel).Trim()
if ($LASTEXITCODE -ne 0 -or -not $repoRoot) {
    throw "Could not find the Git repository root."
}

$entries = & git -C $repoRoot ls-files --eol
if ($LASTEXITCODE -ne 0) {
    throw "Could not list tracked files."
}

$normalizedFiles = [System.Collections.Generic.List[string]]::new()
$candidateFiles = [System.Collections.Generic.List[string]]::new()
foreach ($entry in $entries) {
    $parts = $entry -split "`t", 2
    if ($parts.Count -ne 2 -or $parts[0] -notmatch '\bw/(?:crlf|mixed)\b') {
        continue
    }

    $relativePath = $parts[1]
    if ([System.IO.Path]::GetExtension($relativePath) -in @(".bat", ".cmd")) {
        continue
    }

    $fullPath = [System.IO.Path]::GetFullPath(
        (Join-Path $repoRoot ($relativePath -replace '/', [System.IO.Path]::DirectorySeparatorChar))
    )
    $rootPrefix = [System.IO.Path]::GetFullPath($repoRoot) + [System.IO.Path]::DirectorySeparatorChar
    if (-not $fullPath.StartsWith($rootPrefix, [System.StringComparison]::OrdinalIgnoreCase)) {
        throw "Tracked path resolves outside the repository: $relativePath"
    }
    if (-not (Test-Path -LiteralPath $fullPath -PathType Leaf)) {
        throw "Tracked file is missing from the working tree: $relativePath"
    }

    $bytes = [System.IO.File]::ReadAllBytes($fullPath)
    $output = [System.Collections.Generic.List[byte]]::new($bytes.Length)
    $changed = $false
    for ($index = 0; $index -lt $bytes.Length; $index++) {
        if ($bytes[$index] -eq 13) {
            $changed = $true
            if ($index + 1 -lt $bytes.Length -and $bytes[$index + 1] -eq 10) {
                $index++
            }
            $output.Add(10)
        } else {
            $output.Add($bytes[$index])
        }
    }

    if ($changed) {
        $candidateFiles.Add($relativePath)
        if ($PSCmdlet.ShouldProcess($relativePath, "Normalize line endings to LF")) {
            [System.IO.File]::WriteAllBytes($fullPath, $output.ToArray())
            $normalizedFiles.Add($relativePath)
        }
    }
}

if ($candidateFiles.Count -eq 0) {
    Write-Output "No tracked files needed line-ending normalization."
} elseif ($WhatIfPreference) {
    Write-Output "Preview complete: $($candidateFiles.Count) tracked file(s) would be normalized."
} elseif ($normalizedFiles.Count -eq 0) {
    Write-Output "No files were normalized."
} else {
    Write-Output "Normalized $($normalizedFiles.Count) tracked file(s) to LF:"
    $normalizedFiles | ForEach-Object { Write-Output "  $_" }
}
