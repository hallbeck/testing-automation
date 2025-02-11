#!/usr/bin/env groovy

node {
    stage('Git checkout') { // for display purposes
        git 'https://github.com/tealidragon/testing-automation.git'
    }
    stage('Smoke') {
        try {
            sh "mvn clean verify -Dtags='type:Smoke'"
        } catch (err) {

        } finally {

            publishHTML(target: [
                    reportDir  : 'target/site/thucydides',
                    reportFiles: 'index.html',
                    reportName : "Smoke tests report",
                    keepAll:     true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
            ])
        }
    }
    stage('Db') {
        try {
            sh "mvn clean verify -Dtags='type:DB'"
        } catch (err) {

        } finally {
            publishHTML(target: [
                    reportDir  : 'target/site/thucydides',
                    reportFiles: 'index.html',
                    reportName : "DB tests report",
                    keepAll:     true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
            ])
        }
    }
    stage('API') {
        try {
            sh "mvn clean verify -Dtags='type:API'"
        } catch (err) {

        } finally {
            publishHTML(target: [
                    reportDir  : 'target/site/thucydides',
                    reportFiles: 'index.html',
                    reportName : "API tests report",
                    keepAll:     true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
            ])
        }
    }
    stage('UI') {
        try {
            sh "mvn clean verify -Dtags='type:UI' -Dgrid=true"
        } catch (err) {

        } finally {
            publishHTML (target: [
                    reportDir: 'target/site/thucydides',
                    reportFiles: 'index.html',
                    reportName: "UI tests report",
                    keepAll:     true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
            ])
        }
    }
    stage('Results') {
        junit '**/target/failsafe-reports/*.xml'
    }
}
