# About
This is a demo application built during the onboarding process to get to know Camunda 8.
It uses a Saas-Distribution to run the process model.

The following concepts can be found in this app:

## Concepts
The core feature is to suggest a gaming console based on the birthyear entered.
Core Features and Concepts used in this example are:
- BPMN for Process Modelling
- DMN for suggesting consoles
- using Camunda Forms for displaying data and continuing process instances
- some advanced concepts on FEEL 
  - date mapping for defining new variables inside DRD
  - using common FEEL functions (`includes`) to determine if a given year is in range
  - using FEEL for IO-Mapping for the UserTask to transform a List of String into a specific K,V Json Object so that data can be displayed within a Select-Dropdown of Camunda Forms

## Implementation
- Basic JobWorker implementations and fetching variables
- Basic interactions via the java-zeebe-client
- Exposing custom REST-EP for interacting with Zeebe (staring ProcessInstances)
- running multiple profiles for local development and saas
- customization of Zeebe's Jackson-Mapper to reuse Springs `Objectmapper`instead of instanciating a new one missing relevant Jackson modules
- running Integrationtests with `@ZeebeSpringTest` and interacting with the Engine
- examples on how to handle Connectors and UserTasks within tests

