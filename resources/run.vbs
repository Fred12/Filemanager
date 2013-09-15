Set WshShell = CreateObject("WScript.Shell")
cmds=WshShell.RUN("run.bat", 0, True)
Set WshShell = Nothing