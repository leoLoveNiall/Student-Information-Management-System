@echo off
    powershell ^
        ^<#去掉-r则不处理子文件#^>^
        dir . -exclude '%~nx0' -r^|?{$_ -is [System.IO.FileInfo]}^|%%{^
            $tmpname=($_.BaseName -replace '\D').trim();^
            write-host $_.FullName.replace('%~dp0','.\') -nonewline;^
            if($tmpname -and ($tmpname -ne $_.BaseName)){^
                $newname=$_.DirectoryName+'\'+$tmpname+$_.Extension;^
                $n=0;^
                while(Test-Path -LiteralPath $newname){^
                    $n++;^
                    $newname=$_.DirectoryName+'\'+$tmpname+'_'+$n+$_.Extension;^
                };^
                mv -LiteralPath $_.FullName $newname;^
                write-host (' --^> '+($newname.split('\')[-1])) -ForegroundColor green;^
            }else{^
                write-host ' --^> 为纯汉字或不含汉字，不处理' -ForegroundColor red;^
            }^
        }
    pause