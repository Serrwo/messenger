Starting kafka server:
   1. Edit \config\zookeeper.properties
   2. Set dataDir to 'dataDir<kafka-folder>\zookeeper-data'
   3. Run in separate terminal: .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   4. Run in separate terminal .\bin\windows\kafka-server-start.bat .\config\server.properties 