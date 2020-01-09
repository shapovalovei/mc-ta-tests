## How to run test: 

* You must have installed TrueAutomation client

* Init project use `trueautomation init` command
 
* Run test

```bash
mvn -Dtest=TestWithInitialLocator test

```

Run test without initial locator(use only after run test with initial locators or if you pick elements with Element Picker)
```bash
mvn -Dtest=TestWithoutInitialLocator test

```