# Visitor pattern

## 데이터 구조와 처리를 분리

'처리'가 여러종류라서, 새로운 처리가 필요해질 때 마다, 데이터 구조를 나타내는 클래스 수정해야함

## 🙋‍♂️데이터 구조는 문을 두드리는 "방문자" 만 받아들이기만 하면 된다

**방문자가 데이터 처리 담당**

- 데이터 구조 → **"방문자"가 처리**
- 새로운 처리 추가 → 새로운 **"방문자"** 가 만든다

## 📃 UML

- 방문자
  - Visitor (추상클래스) : 파일이나 디렉토리를 **방문하는 방문자**를 나타낸 추상 클래스
    - visit(File or Directory) : File이나 Directory를 방문했을 때 각각 클래스가 호출하는 메소드
    - 메소드 오버로드(overload) 이용
      - visit 메소드의 이름은 같은데, 인자의 종류가 다름
  - ListVisitor : Visitor 클래스의 하위 클래스로 파일이나 디렉토리의 **알람**을 나타내는 클래스
    - 실제 데이터구조(File or Directory)를 옮겨 다니면서 리스트를 출력함
    - currentdir 필드
      - 현재 주목하고 있는 디렉토리 명을 저장함
    - visit(File)
      - File에 대해 수행해야 할 처리가 기술
      - algo
        - 현재 디렉토리와 File의 toString( ) 반환 값을 연결하여 현재 파일의 전체 경로 출력
    - visit(Directory)
      - Dir에 대해 수행해야 할 처리가 기술
      - algo
        1. 디렉토리 전체 경로 및 크기 출력
        2. 현재 디렉토리(currentdir) 임시로 savedir 저장
        3. currentdir를 입력인자로 들어온 dir로 바꿈
        4. 입력인자로 들어온 디렉토리의 iterator를 얻는다
        5. 입력인자로 들어온 dir가 유지하는 원소들을 차례로 방문하면서, accept(this)를 호출하여 방문자가 방문했음을 알린다
        6. while 루프가 끝나면 currentdir을 원래 dir로 복귀시킨다
      - 복잡한 재귀적인 호출
        - accept 메소드는 visit 메소드를 호출하고 visit 메소드는 accept 메소드 호출
- 데이터구조
  - Element (interface) : Visitor 클래스의 인스턴스를 받아들이는 **데이터 구조**를 나타내는 인터페이스
    - 방문자를 받아들임
    - accept(Visitor v) : Visitor 타입을 입력 인자로 받아들임
  - Entry (추상클래스) : Element 인터페이스를 구현
    - add( )
      - **Directory 클래스에서만 add( )가 유효하므로, Entry에서는 에러로 처리**
    - iterator( )
      - 요소에 대한 Iterator를 얻을 때 호출되는 메소드
      - **Directory 클래스에서만  iterator( )가 유효하므로, Entry에서는 에러로 처리**
  - File : 파일을 나타내는 클래스
    - accept(Visitor v) : 클라이언트가 File 객체에게 **"방문자를 받아들이세요"** 라고 요청할 때 호출하는 메소드
      - 클라이언트는 Visitor를 매개변수로 하여 accept 호출
      - 입력인자로 들어온 방문자의 visit 메소드 호출
        - 현재 자신 객체를 인자로 하여 호출
        - Visitor의 visit(File) 메소드 실행
        - 즉 방문자가 방문하면 방문자에게 "나는 File 객체입니다. 나의 일을 처리해주세요" 라고 요청하는 것과 비슷
  - Directory : 디렉토리를 나타내는 클래스
    - iterator( )
      - 디렉토리가 유지하고 있는 엔트리들에 대한 Iterator를 반환한다
    - accept(Visitor)
      - Visitor의 visit(Directory) 메소드 실행
      - 즉 방문자가 방문하면 방문자에게 "나는 Directory 객체입니다. 나의 일을 처리해주세요" 라고 요청하는 것과 비슷
  - FileTreatmentException : File에 대해서 add한 경우에 발생하는 예외 클래스

## 🤔 Main 클래스

### Composite vs Visitor

- Composite
  - main 메소드가 엔
