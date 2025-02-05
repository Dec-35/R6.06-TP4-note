# Détail des outils & environnement

### IDE

J'ai utilisé IntelliJ IDEA pour développer le projet.

### Java
 
J'ai utilisé Java17 avec le JDK de Amazon Corretto. 

### Gradle

J'ai utilisé Gradle pour gérer les dépendances et lancer les tests.

Voici les dépendances utilisées dans le projet :
- JUnit 5 : Pour les tests unitaires
- AssertJ : Pour les assertions plus poussées
- Jacoco : Pour la couverture de code
- JUnit Jupiter Params : Pour les tests paramétrés (tags)

# Procédure de création de nouveau projet avec Gradle

### Etapes :
1. File > New > Project
2. Définir un nom
3. Cocher la case "Create git repository for project"
4. Choisir Gradle comme type de projet
5. Choisir le JDK de Amazon Corretto
6. Choisir Groovy comme "Grade DSL"

# Procédure pour importer les différentes librairies et plugins utilisés

### Etapes :
1. Ouvrir le fichier `build.gradle`
2. Ajouter les dépendances nécessaires dans la section `dependencies` :
```groovy
dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core:3.24.2'
    testImplementation 'org.junit.jupiter:junit-jupiter-params'
}
```
3. Ajouter le plugin jacoco dans la section `plugins` :
```groovy
plugins {
    id 'java'
    id 'jacoco'
}
```
4. Ajouter la configuration de jacoco dans la section `jacocoTestReport` :
```groovy
jacocoTestReport {
    reports {
        xml.required = true
        html.required = true
    }
}
```

# Avantages des assertions d'AssertJ par rapport à JUnit

AssertJ propose des assertions plus poussées que JUnit. Par exemple, il est possible de vérifier si une liste contient une valeur spécifique, si une liste est vide, si une liste est triée, etc.
AssertJ permet aussi de vérifier l'intéraction entre deux objets, de vérifier si un objet est une instance d'une classe, etc.

# Procédure pour lancer les différents tests avec Gradle et consulter les rapports de test

Vérifiez que votre config gradle contient bien les tâches de test spécifique à vos tags :
```groovy
tasks.register('utilTests', Test) {
    useJUnitPlatform {
        includeTags 'util'
    }
}

tasks.register('agencyTests', Test) {
    useJUnitPlatform {
        includeTags 'agency'
    }
}
```

- Pour lancer les tests généraux, ouvrez l'onglet "Gradle" sur la droite de IntelliJ IDEA, puis double-cliquez sur Tasks > verification > test.
- Pour lancer les tests spécifiques aux tags, ouvrez l'onglet "Gradle" sur la droite de IntelliJ IDEA, puis double-cliquez sur Tasks > other > utilTests ou agencyTests.
- Pour consulter les rapports de test, ouvrez le fichier `build/reports/jacoco/test/html/index.html` dans votre navigateur.