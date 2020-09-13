package org.devops

def SaltDeploy(host, func) {
    sh "salt \"${host}\" ${func}"
}
