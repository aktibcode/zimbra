# Système de Gestion d'École

Une application Java complète pour la gestion administrative d'une école, incluant la gestion des élèves, des notes, des enseignants et des aspects financiers.

## Fonctionnalités

- 👨‍🎓 Gestion des élèves (inscription, suivi)
- 📚 Gestion des classes et des cours
- 📊 Gestion des notes et bulletins
- 👨‍🏫 Gestion des enseignants
- 💰 Gestion financière (recettes, dépenses)
- 📋 Génération de rapports et bilans

## Prérequis

- Java JDK 8 ou supérieur
- MySQL Server
- Eclipse IDE (recommandé)

## Installation

1. Cloner le repository
```bash
git clone [URL_DU_REPO]
cd Gestion_Ecole
```

2. Configurer la base de données
- Créer une base de données MySQL
- Importer le schéma de base de données (fichier SQL à venir)

3. Configuration de l'application
- Copier le fichier `config.properties.example` vers `config.properties`
- Modifier les paramètres de connexion à la base de données dans `config.properties`

## Structure du Projet

```
src/
├── connec/          # Gestion des connexions à la base de données
├── entite/          # Classes modèles (Élève, Enseignant, etc.)
└── metier/          # Logique métier et interfaces utilisateur
```

## Modules Principaux

- **Gestion des Élèves** : Inscription, suivi, orientation
- **Gestion des Notes** : Saisie des notes, calcul des moyennes, bulletins
- **Gestion Financière** : Suivi des recettes et dépenses
- **Rapports** : Génération de bilans et statistiques

## Technologies Utilisées

- Java
- Swing (Interface graphique)
- MySQL
- Apache POI (Export Excel)

## Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. Forkez le projet
2. Créez votre branche (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Poussez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## Licence

Ce projet est sous licence [À DÉFINIR]

## Contact

[Votre Nom/Organisation] - [Votre email]
