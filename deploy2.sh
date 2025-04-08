#!/bin/bash

# Nom de l'application (WAR)
APP_NAME="ETU003369"

# Répertoires du projet
SRC_DIR="src/main/java"          
WEB_DIR="src/main/webapp"        
BUILD_DIR="build"                
LIB_DIR="lib"                    
TOMCAT_WEBAPPS="/opt/tomcat/webapps" 


# Chemin vers les bibliothèques nécessaires
SERVLET_API_JAR="$LIB_DIR/servlet-api.jar"
MYSQL_JAR="$LIB_DIR/mysql-connector-j-9.1.0.jar"  # Assurez-vous que le chemin est correct

# Vérifier que les fichiers JAR existent
if [ ! -f "$SERVLET_API_JAR" ]; then
    echo "Erreur : $SERVLET_API_JAR n'existe pas."
    exit 1
fi
if [ ! -f "$MYSQL_JAR" ]; then
    echo "Erreur : $MYSQL_JAR n'existe pas."
    exit 1
fi

# Étape 1 : Nettoyer le répertoire de build
echo "Nettoyage du répertoire de build..."
rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR/WEB-INF/classes
echo "Répertoire de build nettoyé et créé."

# Étape 2 : Compiler les fichiers source Java
echo "Compilation des fichiers source Java..."
find $SRC_DIR -name "*.java" > source.txt
javac -cp "$SERVLET_API_JAR:$MYSQL_JAR" -d $BUILD_DIR/WEB-INF/classes @source.txt
if [ $? -ne 0 ]; then
    echo "Erreur lors de la compilation des fichiers Java."
    rm source.txt
    exit 1
fi
rm source.txt
echo "Fichiers Java compilés avec succès."

# Étape 3 : Copier les fichiers web dans le répertoire de build
echo "Copie des fichiers web..."
cp -r $WEB_DIR/* $BUILD_DIR/
if [ $? -ne 0 ]; then
    echo "Erreur lors de la copie des fichiers web."
    exit 1
fi
echo "Fichiers web copiés avec succès."

# Étape 4 : Copier les fichiers JAR nécessaires dans le répertoire de build
echo "Copie des fichiers JAR nécessaires..."
mkdir -p $BUILD_DIR/WEB-INF/lib
cp $MYSQL_JAR $BUILD_DIR/WEB-INF/lib/
echo "Fichiers JAR copiés avec succès."

# Étape 5 : Créer le fichier WAR
echo "Création du fichier WAR..."
cd $BUILD_DIR || exit
jar -cvf $APP_NAME.war .
if [ $? -ne 0 ]; then
    echo "Erreur lors de la création du fichier WAR."
    exit 1
fi
echo "Fichier WAR créé avec succès."

# Étape 6 : Déployer le fichier WAR sur Tomcat
echo "Déploiement de l'application sur Tomcat..."
cp $APP_NAME.war $TOMCAT_WEBAPPS/
if [ $? -ne 0 ]; then
    echo "Erreur lors du déploiement sur Tomcat."
    exit 1
fi
echo "Application déployée avec succès."

# Succès
echo ""
echo "Déploiement terminé avec succès. Accédez à vos servlets via les URL suivantes :"
echo "http://localhost:8080/$APP_NAME/index.jsp"