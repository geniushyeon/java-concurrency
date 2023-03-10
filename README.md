# java-concurrency
인프런 &lt;재고시스템으로 알아보는 동시성이슈 해결방법> 수강 레포

## race condition
두 개 이상의 프로세스가 공통 자원을 병행적으로(concurrently) 읽거나 쓰는 동작을 할 때, 공용 데이터에 대한 접근이 어떤 순서에 따라 이루어졌는지에 따라 그 실행 결과가 같지 않고 달라지는 상황
- 두 개의 스레드가 하나의 자원을 놓고 서로 사용하려고 경쟁하는 상태

### Java에서 제공하는 방법
메소드에 `synchronized` 키워드 사용
- but, 스프링의 `Transactional` 어노테이션과 함께 사용하면 동일한 문제 발생
- 하나의 프로세스 안에서만 보장됨
  - 서버가 한 대일때는 괜찮지만, 한 대 +a일 경우 여러 대에서 데이터에 접근할 수 있게 됨
  - 결국 여러 스레드에서 동시에 데이터에 접근할 수 있게 됨
  - 실무에서는 거의 사용하지 않음

### Database 활용하기
#### 1. Pessimistic Lock
실제로 데이터에 Lock을 걸어 정합성을 맞춤
- jpa 쿼리문에 `for update` 구문 포함
#### 2. Optimistic Lock
실제로 Lock을 이용하지 않고 버전을 이용함으로써 정합성을 맞춤
#### 3. Named Lock
이름을 가진 metadata Locking
- 트랜잭션이 종료될 때 Lock이 해제되지 않음
- 주로 분산 락을 구현할 때 사용
- 락 해제, 세션 관리에 주의
### Redis 활용하기
- Lettuce
- Redisson