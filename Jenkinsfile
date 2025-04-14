pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Prepare Env') {
      steps {
        sh '''
          if [ -f /env/.env ]; then
            cp /env/.env .
            export $(grep -v '^#' .env | xargs)
          else
            echo "ERROR: .env file not found at /env/.env"
            exit 1
          fi
        '''
      }
    }

    stage('Build') {
      steps {
        sh '''
          export $(grep -v '^#' .env | xargs)
          ./gradlew clean build
        '''
      }
    }
  }
}