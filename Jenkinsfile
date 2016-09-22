node {
    stage('Checkout and init') {
        git url: 'https://github.com/cannehal/hackathon.git'

        env.PATH = "${tool 'M3'}/bin:${env.PATH}"
    }

    stage('Build') {
        def mvnHome = tool 'M3'
        sh "mvn install -DskipTests"
    }

    stage('Run tests') {
        sh "mvn clean verify -Djava.security.egd=file:/dev/./urandom"
    }

    stage('Deploy springboot') {
        ansiblePlaybook('src/main/ansible/site.yml')
    }

    stage('Test after deploy') {
        sh "mvn verify -PintegrationTests"
    }

}