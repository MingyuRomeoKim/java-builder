# java-builder
gitlab runner 혹은 github actions에서 실행되는 프로그램으로,  push된 business source를 실행 가능하도록 build 및 압축하여 저장하는 프로그램.<br/>
본 파일은 특정 버전의 PHP로 구성된 Laravel Framework만 Build 가능하도록 개발됨.<br/>

# 사용방법
> 1. BuilderManager에 `String yourGitId = ""` 및 `String yourGitPassword` 를 입력 <br/>
> 2. Gradle build를 통해 .jar file 생성 <br/>
> 3. java .jar 명령어 사용하여 properties 값 3개 아래와 같이 넘겨주기 <br/>
>  > args[0] : git repository url <br/>
>  > args[1] : dockerRepo/dockerImage:tag <br/>
>  > args[2] : CI_COMMIT_BRANCH
