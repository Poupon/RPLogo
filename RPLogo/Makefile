all::  RPLogo.class  RPLogoCanvas.class  RPLogoDialogPref.class  RPLogoLangCmd.class  RPLogoManager.class  RPLogoProcess.class  RPLogoTurtle.class  RPLogoTurtlePref.class

clean::
	rm *.class  *.jar

cleanall::
	rm *.class  *.jar ; cd PhiLib/Interface;  make clean;	cd ../PhiLib/Lang;  make clean;	cd ../PhiLib/Util;	make clean;

REP=./

jar:
	rm  RPLogo.jar;jar cfm ${REP}RPLogo.jar ${REP}RPLogo.mf ${REPRPLogo/*.class; jar uf ${REP}RPLogo.jar RPLogo/PhiLib/Interface/*.class RPLogo/PhiLib/Lang/*.class RPLogo/PhiLib/Util/*.class

%.class: %.java
	javac  -classpath "../.." -deprecation -g $*.java
