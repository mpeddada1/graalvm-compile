# graalvm-compile

## Steps to Run
1) Make sure that you're on GraalVM 22.1.0:
```aidl
openjdk version "11.0.15" 2022-04-19
OpenJDK Runtime Environment GraalVM CE 22.1.0 (build 11.0.15+10-jvmci-22.1-b06)
OpenJDK 64-Bit Server VM GraalVM CE 22.1.0 (build 11.0.15+10-jvmci-22.1-b06, mixed mode, sharing)
```
2) `cd child-module`
3) `mvn package -Pnative` to start native image compilation
4) Run `./target/child-project`
