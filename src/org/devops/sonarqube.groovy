package org.devops

def SonarScan(projectName, des, path) {
    withSonarQubeEnv('sonarqube-test') {
        def scannerHome = '/usr/local/sonar-scanner-4.4.0.2170-linux/'
        def sonarServer = 'http://192.168.0.102:9000'
        sonarDate = sh returnStdout: true, script: 'date +%Y%m%d%H%m%s'
        sonarDate = sonarDate - '\n'
        sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=${projectName}  \
            -Dsonar.projectName=${projectName}  \
            -Dsonar.projectVersion=${sonarDate} \
            -Dsonar.ws.timeout=30 \
            -Dsonar.projectDescription=${des}  \
            -Dsonar.links.homepage=http://www.baidu.com \
            -Dsonar.sources=${path} \
            -Dsonar.sourceEncoding=UTF-8 \

            """
    }
}
