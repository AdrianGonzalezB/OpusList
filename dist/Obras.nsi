; The name of the installer
Name "Opus List SPDVI"

; The file to write
OutFile "setup-opuslist-v1.exe"

; Request application privileges for Windows Vista
RequestExecutionLevel user

; Build Unicode installer
Unicode True

; The default installation directory
InstallDir $PROGRAMFILES\OpusList


;--------------------------------

; Pages

Page components
Page directory
Page instfiles
UninstPage uninstConfirm
UninstPage instfiles

;--------------------------------

; The stuff to install

Section "Required files" required_files
	SectionIn RO
	SetOutPath $INSTDIR
	File Obras.jar
	SetOutPath "$INSTDIR\lib"
	File lib\gson-2.8.2.jar
	CreateDirectory "$LOCALAPPDATA\OpusList\data"
	CreateDirectory "$LOCALAPPDATA\OpusList\images"
	WriteUninstaller "$INSTDIR\uninstall.exe"
SectionEnd

Section "Initialize dummy data" dummy_data
	
	SetOutPath "$LOCALAPPDATA\OpusList\data"
	File obres.json
	SetOutPath "$LOCALAPPDATA\OpusList\images"
	File images\*.*
SectionEnd

; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts"

  CreateDirectory "$SMPROGRAMS\OpusList"
  CreateShortcut "$SMPROGRAMS\OpusList\Uninstall.lnk" "$INSTDIR\uninstall.exe"
  CreateShortcut "$SMPROGRAMS\OpusList\OpusList.lnk" "$INSTDIR\Obras.jar"

SectionEnd

;--------------------------------

; Uninstaller

Section "Uninstall"
  
  

  ; Remove files and uninstaller
  Delete "$SMPROGRAMS\OpusList\OpusList.lnk"
  Delete "$SMPROGRAMS\OpusList\Uninstall.lnk"
  Delete $INSTDIR\uninstall.exe
  Delete $INSTDIR\lib\*
  Delete $INSTDIR\Obras.jar

  ; Remove directories
  RMDir $SMPROGRAMS\OpusList
  RMDir $INSTDIR
  RMDir /r $LOCALAPPDATA\OpusList

SectionEnd
