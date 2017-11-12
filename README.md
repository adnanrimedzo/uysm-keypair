# uysm-keypair-generator-validator
## Setup

In order to setup, maven4 and jdk8 are requred.

 1. Built project (at same directory with pom.xml)
    `mvn package`
 2. Run application (at target file)

    2.1. Generate keypair
    `java -jar keypair-1.0.jar -s generate -out key.out -key loremimpsum`
    
    2.2. Generate keypair
    `java -jar keypair-1.0.jar -s validate -in key.out -out result.out`