# Variables
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
MAIN_CLASS = com.packages.main.Main  # Classe principale avec le package complet

# Trouver tous les fichiers .java dans SRC_DIR
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Règles
.PHONY: all run clean

# Compile tous les fichiers .java en une seule commande
all:
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)

# Exécuter la classe principale
run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Nettoyer les fichiers compilés
clean:
	rm -rf $(BIN_DIR)
