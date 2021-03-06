# Algorithm API
What is it?

- A REST Api that serves Algorithm Domain objects from a Mongo Db instance.
- Responses are transformed/formatted at runtime for the end user.
- Please see the [Algorithm-Cloud-Processor](https://github.com/msmele345/algorithm-cloud-processor/) and [Algorithm-Producer](https://github.com/msmele345/algorithm-producer/) apps in my Github for the back end kafka producer and grid loader applications that are tied to this api.
 


### Endpoints:
Request all algorithms:

http://localhost:8080/algorithms/all

**/algorithms/all**

Example Response:
```
[
{
    "name": "countDuplicates",
    "codeSnippet": "fun countDuplicates(inputArray: Array<String>): Int { fun countDuplicates(inputArray: Array<String> : Int = (inputArray.distinct()) - (inputArray.count()) }  }",
    "categoryDescription": "EASY",
    "difficultyLevel": 2,
    "categoryTags": "Tag: String Manipulation
    "solved": true
  },
  {
    "name": "findAverage",
    "codeSnippet": "fun findAverage(num1: Int, num2: Int) : Int = listOf(num1, num2).average()",
    "categoryDescription": "MEDIUM",
    "difficultyLevel": 3,
    "categoryTags": "Tag: String Manipulation, Tag: String Formatting, Tag: Algorithms",
    "solved": true
  }
]
```
Request algorithm by name:

http://localhost:8080/algorithms/popLast

**/algorithms/{name}**

Example Response:
```
{
    "name": "popLast",
    "codeSnippet": "fun popLast(str: String) : String = string.dropLast(1)",
    "categoryDescription": "EASY",
    "difficultyLevel": 2,
    "categoryTags": "Tag: String Manipulation, Tag: String Formatting, Tag: Algorithms",
    "solved": true
  }
```

### Techologies Required:

Java 8
Docker

- The project uses Gradle for it's build system, https://gradle.org/
1. Go to the root project folder

2. Run ./gradlew clean build to build the app

Jars are produced in the algorithm-cloud-processor/build/libs/directory

### Local Setup

1. Go to the algorithm-cloud-processor directory

2. Run ./clean_and_start_docker.sh 
 - Starts Rabbit, Kafka, Mongo Db and Zookeeper services
 
3. Run ./gradlew bootRun to start the app. The app will be running and ready to accept messages from a Kafka topic.

### Setup Docker

**Run** 
./clean_and_start_docker.sh from the command line in the root directory of this project. Note, this will prune all volumes and containers prior to running docker compose.

Please use docker-compose up -d if you do not wish to prune any volumes or containers first.

### Useful Docker commands:
list containers:
docker ps -a 

Stop running container:
docker stop containerid

Remove a non-running container:
docker rm containerid
 
Cleanup docker container space:
volume rm $(docker volume ls -qf dangling=true)

### MongoDb CLI Access and Commands:
```
>>docker exec -it mongodb-1 sh
>>mongo
>>use algorithmDomainModels
(Show all current records:)
>>db.algorithmDomainModels.find() - 
(Delete Duplicates:) 
-db.algorithmDomainModels.find({}, {myCustomKey:1}).sort({_id:1}).forEach(function(doc){ db.myCollection.remove({_id:{$gt:doc._id}, myCustomKey:doc.myCustomKey}); })
```
