package org.devops

//saltstack
def SaltDeploy(host, func) {
    sh "salt \"${host}\" ${func}"
}

//absible
def AnsibleDeploy(host, func) {
    sh "ansible ${func} ${host}"
}
