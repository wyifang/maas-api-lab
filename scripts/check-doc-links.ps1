
$docs = Resolve-Path (Join-Path $PSScriptRoot "..\docs")

$links = Get-ChildItem $docs -Filter *.md -Recurse |
    Select-String -Pattern '\]\(([^)#]+\.md)(#[^)]+)?\)'

$errors = @()

foreach ($match in $links) {
    $relativePath = $match.Matches[0].Groups[1].Value
    $sourceFile = $match.Path
    $targetFile = Join-Path (Split-Path $sourceFile) $relativePath

    if (-not (Test-Path $targetFile -PathType Leaf)) {
        $errors += "$sourceFile -> $relativePath"
    }
}

if ($errors.Count -eq 0) {
    Write-Host "PASS: All checked Markdown links point to existing files."
    exit 0
} else {
    Write-Host "FAIL: Broken Markdown links found:"
    $errors | ForEach-Object { Write-Host $_ }
    exit 1
}
