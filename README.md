# messaging_with_ocp_amq_streams


## Prerequisites

- OpenShift CLI (oc) is installed
  <br/> (see here https://docs.openshift.com/container-platform/4.8/cli_reference/openshift_cli/getting-started-cli.html)
- Java Development Kit and Quarkus is installed and set up
  <br/> (see here https://quarkus.io/get-started/)



## Install AMQ Streams Operator

1. Define OpenShift namespace in ops/kafka-operator.yaml
2. Apply the Kafka (AMQ Streams) operator configuration 
<br/> (make sure you are in the correct namespace)

    ```Shell
    oc apply -f ops/kafka-operator.yaml
    ```

1. Review the pods state to see when the operator is running

    ```Shell
    oc get pods -w
    ```



## Create a Kafka Cluster

1. Define OpenShift namespace in ops/kafka-cluster.yaml
2. Use the OpenShift CLI to apply ops/kafka-cluster.yaml
<br/> (make sure you are in the correct namespace)

    ```Shell
    oc apply -f ops/kafka-cluster.yaml
    ```

3. Review the pods state to see when the Kafka cluster is running

    ```Shell
    oc get pods -w
    ```



## Deploy a Quarkus Java Message Producer

1. Add an extension for Quarkus to integrate with Apache Kafka

    ```Shell
    mvn quarkus:add-extension -Dextension="quarkus-smallrye-reactive-messaging-kafka"
    ```

2. Add an extension for Quarkus to deploy on OpenShift

    ```Shell
    mvn quarkus:add-extension -Dextensions="openshift"
    ```

3. Build and deploy the Quarkus application

    ```Shell
    mvn clean package
    ```

4. Review the pods state to see when the app is running

    ```Shell
    oc get pods -l deploymentconfig=kafka-quarkus -w
    ```

5. Verify the app is running and producing messages


    ```Shell
    oc logs dc/kafka-quarkus -f
    ```

6. Consume messages

    AMQ streams provides container images with the Apache Kafka distribution that include console scripts. We can connect to the running broker to execute a consumer script.

    ```Shell
    oc exec -c kafka my-cluster-kafka-0 -- /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap.kafka.svc.cluster.local:9092 --topic uber --from-beginning
    ```

