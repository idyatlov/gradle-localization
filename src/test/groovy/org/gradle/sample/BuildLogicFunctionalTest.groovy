/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.sample

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.UnexpectedBuildFailure
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class BuildLogicFunctionalTest extends Specification {

    static final PROJECT_ID = "75094"
    static final PUBLIC_KEY = "XUpfNZxOPPJ6YdrJsuvlsJZfmFUhV8DJ"
    static final SECRET_KEY = "zHG4r2Jrb0s5qiEvVocV38oNWNQO5pov"
    static final FILENAME = "strings.xml"

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    @Unroll
    def "can't execute export task with #projectId, #publicKey, #secretKey, #toTranslate"() {
        given:
        buildFile << """
            plugins {
                id 'com.github.idyatlov.onesky-plugin'
            }
            onesky {
                ${projectId ? "projectId \"$projectId\"" : ""}
                ${publicKey ? "publicKey \"$publicKey\"" : ""}
                ${secretKey ? "secretKey \"$secretKey\"" : ""}
                ${toTranslate ? "toTranslate \"$toTranslate\"" : ""}
            }
        """
        println buildFile.text

        when:
        GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments("export")
                .withPluginClasspath()
                .build()

        then:
        thrown(UnexpectedBuildFailure)

        where:
        projectId   | publicKey | secretKey | toTranslate
        null        | PUBLIC_KEY| SECRET_KEY| FILENAME
        PROJECT_ID  | null      | SECRET_KEY| FILENAME
        PROJECT_ID  | PUBLIC_KEY| null      | FILENAME
        PROJECT_ID  | PUBLIC_KEY| SECRET_KEY| null
    }

    def "can execute export task with all defined attributes"() {
        given:
        buildFile << """
            plugins {
                id 'com.github.idyatlov.onesky-plugin'
            }
            onesky {
                projectId "$PROJECT_ID"
                publicKey "$PUBLIC_KEY"
                secretKey "$SECRET_KEY"
                toTranslate "$FILENAME"
            }
        """

        when:
        def result = GradleRunner.create()
        //.withGradleVersion(gradleVersion)
                .withProjectDir(testProjectDir.root)
                .withArguments("export")
                .withPluginClasspath()
                .build()

        then:
        result.task(":export").outcome == SUCCESS
    }
}
