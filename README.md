# 야구 경기 예매 서비스 (22.12.03 ~ 22.12.09)

## 개요

평소에 관심있는 야구 경기라는 도메인과 배울 점이 많을 것 같은 예매를 결합하여서 프로젝트 주제를 선정하였음. 항상 야구장 좌석 후기 서비스를 프로젝트로 진행해보고 싶은 생각을 갖고 있었는데, 그 주제를 진행하기에
앞서 초석으로 진행해보았음.

## 프로젝트 환경

- Java 17
- SpringBoot 2.7.6
- MySQL 8.0
- H2
- JDBC
- Gradle

## 요구사항

- 관리자
    - 경기를 등록할 수 있다.
    - 경기 정보를 수정할 수 있다.
    - 경기를 삭제할 수 있다.
    - 고객, 팀, 경기, 날짜 정보에 따라 예매된 티켓을 조회할 수 있다.
    - 예매된 티켓 정보를 수정할 수 있다.
    - 예매된 티켓을 삭제할 수 있다.

- 고객
    - 팀, 경기장, 날짜 정보에 따라 경기를 조회할 수 있다.
    - 경기 정보에 따라 좌석을 조회할 수 있다.
    - 좌석을 예매할 수 있다.
    - 자신의 예매 내역을 조회할 수 있다.
    - 자신의 예매 내역을 수정할 수 있다.

## ERD

![ERD](https://user-images.githubusercontent.com/82152173/208377971-2ca79edc-ef2c-460e-b304-992642c6c3bc.png)

## 느낀점

시작하기 전에는 나름 간단할 줄 알고 시작했는데, 초심자의 오만한 판단이었다. 예매라는 것이 얼마나 신경써야 할 것이 많은지 느꼈다. 그리고 아예 생판 처음부터 내가 테이블도 설계하고 요구사항도 분석하니, 테이블과
요구사항이 주어졌을 때랑은 느낌이 전혀 달랐다. 테이블을 잘 설계하는 것이 정말 어려운 일이구나. 내가 데이터베이스에 관한 지식이 많이 부족하단 것을 새삼 느꼈다.

JDBC 가 너무 불편했다. 다만, 불편함을 느낀만큼 JPA 가 왜 등장했는지 절실히 느꼈다. SQL 작성하는 데에도 많은 시간을 들여야하고, 오타에도 정말 취약하다. 결정적으로 가장 불편했던 것은 객체와 DB
테이블 간에 매핑할 때, 객체를 DB 테이블처럼 설계해야했다는 점이다. 다른 객체 정보를 필드로 가질 때 참조 값으로 가지지 못하고, 마치 테이블처럼 id 값을 가지게 되는 것이 너무나 불편했다.

이번 프로젝트를 하면서는 해야하는 것을 알면서도 하지 않은 것들이 있다. 검증, 예외처리, 테스트, 동시성, 문서화이다. 쓰고 보니 사실상 전부라서 무엇을 했나 싶기도 하다. 다음 과제는 게시판 과제인데 무조건
이것들을 다 적용해볼 것이다.

## 고민, 생겼던 문제

- **Repository CRUD 시 반환 값은 어떻게 해야할까?**
    - 커맨드 쿼리 분리 원칙(CQS) 이란 것을 구글링을 하다가 알게되었다. 명령 로직과 조회 로직을 분리하는 것이 유지 보수에 좋다는 것이다. 내가 항상 하던대로 Create 메서드에서 생성해준 객체를
      반환해준다면 그건 생성과 조회의 책임을 다 가지니 이 원칙에 어긋난다고 할 수 있다. 근데, 내 생각에는 아무리 그래도 Create 에서 void 를 반환하기보다는 id 정도는 반환해주는 것이 개발
      편의성에 좋을 것 같다. 다만 여기서 추가적으로 생긴 의문은 그럼 JPA 에서는 왜 save 시에 객체를 반환해줄까?


- **조건에 따른 조회 시 모든 데이터 조회 후 거르는 것이 나을까, Repository 에 메서드를 추가해주는 것이 나을까?**
    - 두 경우다 똑같이 쿼리가 한번씩 나가고 대량의 데이터가 있을수록 무조건 후자가 성능적으로 좋긴하다. 다만 맘에 걸렸던 점은 조건이 많아질수록 늘어나는 Repository 의 메서드와 SQL 들이다. 아마
      이런 문제점때문에 동적 쿼리란 것이 존재하는 게 아닐까 생각했다. JDBC 를 이용하여서 한계가 있지만 나름 동적 쿼리 비슷하게 로직을 구현해보았다. 조회 메서드 안에서 StringBuilder 를
      이용하여서 파라미터로 넘어온 조건이 있을 시에 where 조건을 추가해주고 아닌 경우에는 넘어가는 방식의 로직이다. StringBuilder 는 멀티쓰레드 환경에서 안전하지 않을 수 있지만 메서드의
      지역변수로 만들어서 안전하게 처리하였다.
  ``` java 
  public List<Match> findBy(LocalDateTime startTime, String stadiumName, String team) {
    Map<String, Object> queryMap = new HashMap<>();
    StringBuilder queryBuilder = new StringBuilder("select * from matches where 1=1");
  
    if (startTime != null) {
        queryBuilder.append(" and start_time = :startTime");
        queryMap.put("startTime", startTime);
    }
  
    if (stadiumName != null) {
        queryBuilder.append(" and stadium_id = :stadiumId");
        queryMap.put("stadiumId", Stadium.getStadiumByName(stadiumName).getId());
    }
  
    if (team != null) {
        queryBuilder.append(" and (home_team_name = :team or away_team_name = :team)");
        queryMap.put("team", team);
    }
  
    return jdbcTemplate.query(queryBuilder.toString(), queryMap, matchRowMapper);
  }
  ```

- **Entity 와 DTO 변환 로직의 위치**
    - RequestDto -> Entity 의 로직은 RequestDto 가 갖는게 옳다고 본다. 다만, 같은 방식으로 Entity -> ResponseDto 의 로직을 Entity 가 갖는게 옳을까? Dto
      는 데이터 전송 객체로 서비스의 상황에 따라서 변하는 일이 많이 생길 수 있다. 변환 로직을 Entity 가 같는다면 DTO 의 변화때문에 Entity 코드가 변해야하기에 옳지 못한 방식이다. 대신에
      ResponseDto 안에서 파라미터로 Entity 를 받아서 static 메서드로 구현하는 방식을 생각해볼 수 있다.
    - 아니면 아예 Converter 클래스로 변환 로직을 분리할 수도 있다. 여러가지 방식을 생각해보고 내린 결론은 정답이 없는 문제라는 것이다. Entity 내에서 변환 로직을 가지는 명확하게 잘못된 방식을
      제외하고는 다 나름의 장점이 있고, 그렇기에 선택의 문제라고 생각한다.


- **Auto increment 로 아이디 생성 시, test 시 아이디 값 모르게 되는 이슈**
    - KeyHolder 이용하여서 해결하였다.
  
## 아쉬웠던 점, 추후 개선할 점

- **검증 로직 없음**
    - 생성, 수정 등의 작업에서 입력될 수 있는 값에 대한 검증을 전혀 하지 않았다. 무조건 다음 과제에서는 검증을 적용해보자. 혹은 추후 이 프로젝트를 개선하게 된다면 꼭 해야할 부분이다.


- **예외처리 없음**
    - 백엔드 개발에서 중요한 검증, 예외처리를 하지 않고 정상 입력 값에 대한 api 만 구현한 것에 대한 부끄러움을 이 글을 쓰면서 느낀다. 다음 과제 에서는 꼭 적용해보자.


- **테스크 코드 없음**
    - 테스트 코드가 전무하다. 이건 정말 반성해야할 문제다. 어플리케이션을 띄워서 postman 으로 계속 검사해볼 시간에 테스트 코드를 짜자. 역설적으로 테스트 코드 없이 프로젝트를 진행하면서 테스트 코드의
      중요성을 크게 느꼈다. 코드에 변화가 생기거나 기능을 추가할 떄마다 마음 속에서 "아 테스트 코드 있었으면.." 을 얼마나 외쳤는지 모르겠다.


- **동시성 이슈 처리 못함**
    - 예매 서비스에서 어쩌면 가장 중요하다고 할 수 있는 것은 동시성 문제이다. 이 문제를 해결해야한다는 것을 알면서도 DB 설계와 api 구현하기에 급급하여서 시도조차 하지 못했다.