# BPM API Specification 

## Author

Guilherme Silva Guini

## Overview

There are many tools of BPM, and this is not one of them.

This project is just for viewing yours little workflows, sometimes we add or created workflows with few steps, and we don't want to use this out-of-box tools.

And as code is alive, this library help us to keep the documentation update.

### Spring Integration

This project was created for easily integration with spring.

## Get Started

1. First add this library to your project

```
<dependency>
   <groupId>br.com.guini.bpmspecification</groupId>
   <artifactId>bpm-api-specification-spring</artifactId>
   <version>{version}</version>
</dependency>
```

2. Enable feature in any spring application configuration class

```
@EnableBpmApi // enable bpm api
@Configuration
public class BpmApiConfig {

    /**
     * Configure the classes that will be mapped
    */ 
    @Bean
    public BpmConfiguration bpmConfiguration() {
        return BpmConfiguration.config("Test Workflow", PaymentWorkflow.class, ValidationWorkflow.class);
    }
}

```

3. Configure the workflows with bpm annotations

```
@BpmWorkflow(name = "PaymentWorkflow",
        value = {@BpmOperation(name = "Payment Approval"),
                @BpmOperation(name = "Approved", type = BpmOperationType.CONDITION)
        }, satisfiedCondition = {
        @BpmConditionOperation(value = {@BpmOperation(name = "Create Payment")},
                parent = @BpmOperation(name = "Approved"))},
        unsatisfiedCondition = {
                @BpmConditionOperation(value = {@BpmOperation(name = "Notify Rejection")},
                        parent = @BpmOperation(name = "Approved"))
        })
```

4. Result

![workflow image](/doc/workflow.png)



## Annotations

@BpmWorkflow annotation represents one workflow, this annotation can have many @BpmOperation (normal operations) and in this initial version only one @BpmConditionOperation (condition operations).

In Condition Operation you must fill parent attribute.

