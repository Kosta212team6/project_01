CREATE TABLE Member (
	mid	varchar2(30)	CONSTRAINT MID_PK_MEMBER PRIMARY KEY, -- 아이디
	mname	varchar2(30)	NOT NULL, -- 이름
	mphone	varchar2(20), -- 전화번호
	mpwd	varchar2(30)	NOT NULL, -- 비밀번호
	mable	date	NOT NULL, -- 대여가능일
	mstatus	number(1)	NOT NULL, -- 회원상태
    ncode number(2) CONSTRAINT NCODE_FK_MEMBER REFERENCES Notify(ncode) -- 안내코드
);

drop table Member;
select * from Member;

CREATE TABLE Rsv (
	rsvnum	number(4)	CONSTRAINT RSVNUM_PK_RSV PRIMARY KEY, -- 예약번호
	rsvdate	date	NOT NULL, -- 예약일
	mid	varchar2(30)    CONSTRAINT MID_FK_RSV REFERENCES Member(mid), -- 아이디
	bisbn	number(15)	CONSTRAINT BISBN_FK_RSV REFERENCES Book(bisbn) -- ISBN
);

drop table Rsv;
select * from Rsv;

create sequence RSVNUM_SEQ nocache; -- 예약번호 시퀀스
SELECT RSVNUM_SEQ.currval FROM DUAL;
drop sequence RSVNUM_SEQ;

CREATE TABLE Book (
	bisbn	number(15)  CONSTRAINT BISBN_PK_BOOK PRIMARY KEY, -- ISBN
	bname	varchar2(400)	NOT NULL, -- 도서명
	bwrite	varchar2(60)	NOT NULL, -- 저자
	bpub	varchar2(60)	NOT NULL, -- 출판사
	bdate	varchar2(20)	NOT NULL, -- 발행년월일
	bstatus	number(1)	NOT NULL, -- 대출가능여부
	scode	number(3)	CONSTRAINT SCODE_FK_BOOK REFERENCES Sort(scode) -- 분류코드
);

drop table Book;

CREATE TABLE Rent (
	rnum	number(10)	CONSTRAINT RNUM_PK_RENT PRIMARY KEY, -- 대여번호
	rdate	date	NOT NULL, -- 대여일자
	rexdate	date	NOT NULL, -- 반납예정일
	rstatus	number(1)	NOT NULL, -- 대여상태
	bisbn	number(15)	CONSTRAINT BISBN_FK_RENT REFERENCES Book(bisbn), -- ISBN
	mid	varchar2(30)	CONSTRAINT MID_FK_RENT REFERENCES Member(mid) -- 아이디
);

create sequence RNUM_SEQ nocache; -- 대여번호 시퀀스
SELECT RNUM_SEQ.currval FROM DUAL;

drop sequence RNUM_SEQ;

CREATE TABLE Sort (
	scode	number(3)	CONSTRAINT SCODE_PK_SORT PRIMARY KEY, -- 분류코드
	sname	varchar2(30)	NOT NULL -- 분류명
);

drop table Sort;

CREATE TABLE Return (
	rnum	number(10)	CONSTRAINT RNUM_FK_PK_RETURN PRIMARY KEY REFERENCES Rent(rnum), -- 대여번호
	rtdate	date	NOT NULL, -- 반납일자
	bisbn	number(15)	CONSTRAINT BISBN_FK_RETURN REFERENCES Book(bisbn) -- ISBN
);

SELECT * FROM RETURN;
drop table Return;

CREATE TABLE Notify (
    ncode number(2) CONSTRAINT NCODE_PK_NOTIFY PRIMARY KEY,
    ncontent varchar2(60) NOT NULL
);

SELECT * FROM NOTIFY;
DROP TABLE NOTIFY;

select * from book;
select * from member;
select * from rent;
select * from return;
select * from rsv;
select * from sort;
select * from notify;

select * from book where scode='700';

drop table book;
drop table member;
drop table rent;
drop table return;
drop table rsv;
drop table sort;


--------------------------------------------------------------------------------

insert into BOOK values(9791186639702,'하고싶은대로 살아도 괜찮아','윤정은','애플북스','2019-11-04',1 , 100);
insert into BOOK values(9788936434267,'아몬드','손원평','창비','2017-03-31', 1, 200);
insert into BOOK values(9788992969536,'내가만난 1%의 사람들','아담J.잭슨','산솔미디어','2020-02-27',1 , 300); 

-- 도서분류
insert into Sort values(100, '시');
insert into Sort values(200, '소설');
insert into Sort values(300, '자기계발');

INSERT INTO MEMBER values('admin', '관리자', '01012345678','1234',sysdate, 2, 10);
INSERT INTO MEMBER values('lee', '이승현', '01011112222','1111',sysdate, 1, 10);
INSERT INTO MEMBER values('choi', '최수빈', '01033334444','2222',sysdate, 1, 10);
INSERT INTO MEMBER values('shim', '심기훈', '01055556666','3333',sysdate, 0, 10);

--------------------------------------------------------------------------------

SELECT NCONTENT 
FROM NOTIFY JOIN MEMBER 
using(ncode)
WHERE MID='choi';

/*
    책 대여
    choi 가 책 아몬드 한 권을 대여한다. isbn : 9788936434267
*/
SELECT * FROM BOOK WHERE BISBN='9788936434267';
-- 책 정보 입력 할 때 결과 출력시 BSTATUS 함께 체크 : 1일 경우 대여가능 출력

-- 대여하기
INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID)
VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, 9788936434267, 'choi');

SELECT SYSDATE+7 FROM DUAL;

-- 책 대여상태 바꾸기
UPDATE BOOK SET BSTATUS = 0 WHERE BISBN=9788936434267;

-- 내가 대여한 책의 현황 출력
SELECT RENT.RNUM, RENT.REXDATE, BOOK.BNAME
FROM RENT JOIN BOOK
USING(BISBN) WHERE MID='choi';

SELECT * FROM BOOK
WHERE BSTATUS = 1;

/*
    책 반납
    예약되지 않은 책을 반납함
*/
-- 반납 데이터베이스에 업로드
INSERT INTO RETURN(RNUM, RTDATE, BISBN)
VALUES(RNUM_SEQ.CURRVAL, SYSDATE+1, 9788936434267);
-- 대여 상태변수 변경
UPDATE RENT SET RSTATUS = 0 WHERE MID='choi';
-- 예약이 존재하는지 확인 RETURN, RSV 자바에서 .next()써서 null이면 예약 없음
SELECT MID, BISBN, RSVNUM, RSVDATE
FROM RETURN JOIN RSV
USING(BISBN);
-- 책 상태변수 증가
UPDATE BOOK SET BSTATUS = 1 WHERE BISBN=9788936434267;

select * from return;
select * from rent;
select * from rsv;


-- 대여중인 책 보기 -> 누가 어떤 책을 대여했는지 반납했는지를 보는 것

-- 어떤 사용자가 어떤 책을 언제 반납했는가?
SELECT * FROM RETURN;
SELECT * FROM BOOK;
SELECT * FROM RENT;

SELECT RTDATE, RENT.MID, BOOK.BNAME, RENT.BISBN, RENT.RSTATUS
FROM RETURN JOIN BOOK
USING(BISBN) JOIN RENT
USING(RNUM) WHERE MID='choi';

/*
    예약
    : 책을 대여할 때 이미 대출중인 도서일 경우 예약 안내
*/
-- 대여하기
INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID)
VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, 9788936434267, 'choi');

-- 책 대여상태 바꾸기
UPDATE BOOK SET BSTATUS = 0 WHERE BISBN=9788936434267;

-- 내가 대여한 책의 현황 출력
SELECT RENT.RNUM, RENT.REXDATE, BOOK.BNAME
FROM RENT JOIN BOOK
USING(BISBN) WHERE MID='choi';

SELECT * FROM BOOK
WHERE BSTATUS = 1;

-- 빌린 책을 다른 사람이 대여
SELECT * FROM BOOK WHERE BISBN='9788936434267';
-- BSTATUS 체크 : 0이라서 대여 불가 뜸. 예약하시겠습니까?

-- 메인 메뉴 -> 예약메뉴 선택
-- y를 누르면 책 정보가 남아있는 상황. 이걸 받아서 바로 검색
-- resultSet 해서 담은 정보를 그대로 예약으로 인수 넘김

INSERT INTO RSV(RSVNUM, RSVDATE, MID, BISBN) VALUES(RSVNUM_SEQ.NEXTVAL, SYSDATE, 'lee', 9788936434267);

-- 반납 데이터베이스에 업로드
INSERT INTO RETURN(RNUM, RTDATE, BISBN)
VALUES(RNUM_SEQ.CURRVAL, SYSDATE+1, 9788936434267);
-- 대여 상태변수 변경
UPDATE RENT SET RSTATUS = 0 WHERE MID='choi';

-- 예약이 존재하는지 확인 RETURN, RSV 자바에서 .next()써서 null이면 예약 없음
SELECT MID, BISBN, RSVNUM, RSVDATE
FROM RETURN JOIN RSV
USING(BISBN);

-- 예약이 존재하므로 메세지 날림
UPDATE MEMBER SET ncode=100 WHERE MID='lee';


/*
    로그인
    : 들어오자마자 메세지 컬럼 실행
*/

-- 로그인 시 정보 뽑는 메소드
select mid, mpwd, mstatus, ncontent from Member join notify 
using(ncode)
where mID='choi' and mPwd=2222;

select mid, mpwd, mstatus from Member where mID='choi' and mPwd=2222;

commit;

rollback;


group by bisbn order by rsvdate rownum

/*
책 데이터 입력
INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791186639702,'하고싶은대로 살아도 괜찮아','윤정은','애플북스','2019/11/4',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788936434267,'아몬드','손원평','창비','2017/3/31',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788992969536,'내가 만난 1%의 사람들','아담J.잭슨 저, 장연','산솔미디어','2020/2/27',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791196990732,'나는 된다 잘된다','박시현','유노북스','2020/4/6',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791190630955,'밤은 이야기하기 좋은 시간이니까요','이도우','위즈덤하우스','2020/3/31',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791130629346,'어둠의 눈','딘 쿤츠','다산책방','2020/4/10',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788932316758,'나는 고양이로소이다','나쓰메 소세키','새움','2020/1/16',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788970127248,'총, 균, 쇠','재레드 다이아몬드','문학사상사','2005/12/19',1,900);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788970654409,'징비록','유성룡','역사/문화','2015/2/17',1,900);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791158360214,'이상한 엄마','백희나','책읽는곰','2016/3/15',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788961700023,'부릉부릉 자동차가 좋아','리처드 스캐리','보물창고','2007/10/30',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791188700592,'하루 3줄 초등 글쓰기의 기적','윤희솔','청림라이프','2020/2/5',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791186494431,'세상에서 제일 재밌는 종이접기','이원표','슬로래빗','2018/8/24',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791165210991,'수학의 쓸모','닉폴슨, 제임스 스콧','더퀘스트','2020/4/22',1,400);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788937488061,'떨림과 울림','김상욱','동아시아','2018/11/7',1,400);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788962622508,'야생의위로','에마 미첼','심심','2020/3/20',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791156758174,'놓아 버림','데이비드 호킨스','판미동','2013/10/10',1,200);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788960179110,'Evolutionary Game Theory (Paperback, Revised)','Jorgen W. Weibull','Mit pr','1997/8/21',1,300);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9780262731218,'EBS 수능특강 수학영역 확률과 통계 (2020년)','EBS(한국교육방송공사)','한국교육방송공사','2020/1/16',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788954751964,'발효빵 / 케이크 / 타르트 / 파이 ','투보이맘 우상님 ','경향BP','2018/8/2',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788969522641,'밤과 꿈의 뉘앙스','박은정','민음사㈜','2020/2/28',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788937408885,'해리 포터 시리즈 세트 - 전11권 (양장) ','j.k.롤링','문학수첩','2014/12/22',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788901240978,'우리는 달에 가기로 했다','리처드 와이즈먼','리더스북','2020/3/27',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (97889349629842,'어제까지의 세계 (양장)','재레드 다이아몬드','김영사㈜','2013/5/19',1,900);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791161754000,'Go 풀스택 웹 개발','미나 안드라오스','에이콘출판','2020/3/26',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788934995500,'말하지 않고 동물과 대화하는 법 ','피 호슬리','김영사㈜','2020/4/8',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791186201640,'니이름이 뭐라고?!','케스 그레이','로이북스','2020/1/10',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791196844875,'신기한 곤충도감','신카시 타카시','진선아이','2020/3/17',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788905045111,'풍산자 수학(상)','풍산자','지학사','2017/1/15',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791162402252,'자이스토리 고3 수학1','윤장노, 윤혜미','수결 출판사','2019/2/11',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788973435234,'심리학으로 읽는 삼국지','이동연','평단문화사','2020/2/28',1,900);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791156120971,'번역으로서의 동아시아','후나야마 도루','푸른역사','2018/6/29',1,900);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788930040006,'숲에 산다','조상호','나남 출판사','2020/2/25',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791187119845,'나는 나로 살기로 했다','김수현','마음의 숲','2016/11/25',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791170430629,'당신에게 베토벤을 선물합니다.','임현정','페이스메이커','2020/3/5',1,600);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791190403917,'조리도구의 세계','이용재','반비 출판사','2020/3/2',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791196978709,'파스타의 기초','안토니오 심','맛있는 책방','2020/3/2',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791196934804,'정의를 찾는 소녀','유범상','마북 출판사','2020/2/25',1,300);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788950986735,'심장에 수놓은 이야기','구병모','아르테','2020/3/18',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791156624462,'우린 언제가 화성에 가겠지만','김강','도서출판','2020/3/31',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791165075064,'녹나무의 파수꾼','히가시고 게이고','소미미디어','2020/3/17',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (8937416085,'미디어의 이해','마샬 맥클루언','커뮤니케이션북스','2011/4/22',1,300);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788970840659,'서양미술사','에른스트 H. 곰브리치','예경','2003/7/10',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788937460838,'팡세','블레즈 파스칼','민음사','2003/8/25',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788947540674,'오리지날스','애덤 그랜트','한경비피','2016/2/2',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791156642459,'운영체제','Abraham Silberschatz','퍼스트북','2020/2/28',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788964212134,'컴퓨터 구조 및 설계','David A. Patterson','한티미디어','2015/1/19',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788949714233,'리바이어던','토마스 홉스','서해문집','2007/9/15',1,900);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788932003979,'입 속의 검은 잎','기형도','문학과지성사','2000/6/30',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791189998172,' 차라투스트라는 이렇게 말했다','프리드리히 니체','민음사','2004/1/2',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788972917038,'소유냐 존재냐','에리히 프롬','문예출판사','2020/2/3',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791165210564,'2020 시나공 정보처리기사 실기','강윤석','길벗','2020/2/17',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788931009521,'사람은 무엇으로 사는가','레프 톨스토이','문예출판사','2015/6/20',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791155424353,'바이러스 쇼크','최강석','매일경제신문사','2020/2/1',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791186217627,' 닥치고 데스런 DeSLun ','조성준','더디퍼런스','2015/1/27',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788935657087,'불안의 개념/죽음에 이르는 병','키에르케고르','키르케고르','2016/9/9',1,100);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791160074079,'D의 살인사건, 실로 무서운 것은','우타노 쇼고','한즈미디어','2019/10/25',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788901205359,'문득 사람이 그리운 날엔 시를 읽는다2','박광수','걷는나무','2019/9/24',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791125324447,'주니어 리딩튜터 Junior Reading Tutor Level 1','능률','NE능률','2019/1/5',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791157843374,'내가 가는 길이 꽃길이다','손미나','한빛비즈','2019/6/14',1,800);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788950936211,'한근태의 인생 참고서','한근태','21세기북스','2012/3/29',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791188990337,'역사를 재미난 이야기로 만든 사람들에 대한 역사책','정기문','책과함께','2012/3/24',1,900);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791130308968,'사회과학 연구방법론','구정화,김상원','박영사','2018/8/17',1,300);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788998171056,'사회책임의 시대, ISO 26000 이해와 활용','황상규','틔움','2013/4/18',1,300);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791164160457,'내 몸과의 전쟁','피지컬갤러리','책들의정원','2019/12/31',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791186925058,'더 플라워 스쿨 아네트','아네트','책밥','2016/6/10',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791190630016,'백종원의 도전 요리왕4:미국','백종원,남지은','위즈덤하우스','2020/06/06',1,0);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791161659145,'종교가 나에게 말을 걸어올 때','김봉현','지식의 숲','2020/3/25',1,200);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9788963193489,'양자역학은 처음이지?','곽영직','북멘토','2020/3/17',1,400);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791157764723,'당신의 가정도 행복할 수 있다.','권영세','책과나무','2018/5/17',1,500);

INSERT INTO BOOK (BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES (9791190403559,'운명의 그림','나카노 교코','세미콜론','2020/4/13',1,600);
*/


SELECT * FROM BOOK;

commit;
CREATE TABLE Notify (
	ncdoe	number(2)	CONSTRAINT NCODE_PK_NOTIFY PRIMARY KEY, -- 아이디
	ncontent varchar2(60)
);
Select * from sort;
 -- 분야별 검색
 select BNAME 도서명,  BWRITE 저자, BPUB 출판사, BDATE 발행일, BISBN ISBN,sname 분류명, SCODE 분류코드
 from book
 natural join sort
 where sname='역사';
 --도서명
 select BNAME 도서명,  BWRITE 저자, BPUB 출판사, BDATE 발행일, BISBN ISBN,sname 분류명, SCODE 분류코드
from book
 natural join sort
 where bname like '%당신의 가정도 %';
 
 --저자
 select BNAME 도서명,  BWRITE 저자, BPUB 출판사, BDATE 발행일, BISBN ISBN,sname 분류명, SCODE 분류코드
 from book
 natural join sort
 where BWRITE like '%김%';
 
 -- 출판사
 select BNAME 도서명,  BWRITE 저자, BPUB 출판사, BDATE 발행일, BISBN ISBN,sname 분류명, SCODE 분류코드
 from book
 natural join sort
 where BPUB like '%민음%';
 
 select * from rent;
 --내가 대여중인 도서
 select MID 사용자계정, BNAME 도서명, BWRITE 저자, BPUB 출판사, RDATE 대여일, BISBN ISBN 
 from book
 natural join rent
 where mid = 'choi' and rstatus =1;
 
 --내가 반납한 도서.
  select MID 사용자계정, BNAME 도서명, BWRITE 저자, BPUB 출판사, RDATE 대여일, RTDATE 반납일,BISBN ISBN 
 from book
 natural join rent
 natural join return
 where mid = 'choi' and rstatus =0;
 
 select * from return;
 

/**
    관리자 메뉴 쿼리
*/

/*
    회원조회
*/
SELECT * FROM MEMBER;
-- 전체회원조회
SELECT MID, MNAME, MPHONE, MABLE, NCODE FROM MEMBER WHERE MSTATUS=1;
-- 연체회원조회
SELECT MID, MNAME, MPHONE, MABLE, NCODE FROM MEMBER WHERE MSTATUS=1 AND SYSDATE < TO_CHAR(MABLE, 'YYYYMMDD'); 

SELECT DUMMY FROM DUAL WHERE '20210304' > TO_CHAR(SYSDATE, 'YYYYMMDD');

-- 회원 아이디 중복체크
SELECT MID FROM MEMBER WHERE MID=?;

-- 회원 추가
INSERT INTO MEMBER(MID, MNAME, MPHONE, MPWD, MABLE, MSTATUS, NCODE) VALUES(MID, MNAME, MPHONE, MPWD, MABLE, MSTATUS, NCODE);

delete from member where mname='망고';

commit;

-- 내 정보 열람
SELECT * FROM MEMBER;
SELECT MID, MNAME, MPHONE, MABLE FROM MEMBER WHERE MID='choi';
-- 내 정보 수정
UPDATE MEMBER SET MPHONE='01055555555' WHERE MID='choi';
UPDATE MEMBER SET MPWD='2222' WHERE MID='choi';
-- 탈퇴하기
-- 비밀번호 재입력
select mid, mpwd, mstatus from Member where mID='choi' and mPwd=2222;
-- 회원상태 변경
UPDATE MEMBER SET MSTATUS=0 WHERE MID='' AND MPWD='';

/*
    도서 추가
*/
SELECT * FROM BOOK;
INSERT INTO BOOK(BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SCODE) VALUES();

/*
    도서 수정 
*/
2147483647

-- ISBN
UPDATE BOOK SET BISBN=416085333 WHERE BISBN=416085;
-- 도서명
UPDATE BOOK SET BNAME=? WHERE BISBN=999999999;
-- 저자
UPDATE BOOK SET BWRITE=? WHERE BISBN=999999999;
-- 출판사
UPDATE BOOK SET BPUB=? WHERE BISBN=999999999;
-- 발행년월일
UPDATE BOOK SET BDATE=? WHERE BISBN=999999999;
-- 분류코드
UPDATE BOOK SET SCODE=? WHERE BISBN=999999999;

/*
    도서 삭제
*/
DELETE FROM BOOK WHERE BISBN=?;

select substr(bisbn, 5, 9) from book;
UPDATE BOOK SET BISBN=SUBSTR(BISBN, 5, 9);


/*
    도서 검색
*/
-- ISBN 검색
SELECT BISBN, BNAME, BWRITE, BPUB, BDATE, BSTATUS, SNAME
FROM BOOK JOIN SORT
USING(SCODE) WHERE BISBN=936434267;



--------------------------------------------------------------------------------
-- 시퀀스 초기화 RSVNUM_SEQ RNUM_SEQ
SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME='RNUM_SEQ';
ALTER SEQUENCE RNUM_SEQ INCREMENT BY -28;
SELECT RNUM_SEQ.CURRVAL FROM DUAL;
ALTER SEQUENCE RNUM_SEQ INCREMENT BY 1;

/*
    대여한 도서 보기
*/
select * from return;
SELECT * FROM RENT;
SELECT RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID FROM RENT WHERE MID='choi';

-- 대여 예시 추가
delete rent where mid='lee';

commit;

/*
    예약한 도서 보기
*/
select * from book;
SELECT * FROM RSV;
SELECT RSVNUM, RSVDATE, MID, BISBN, BNAME, BSTATUS
FROM RSV JOIN BOOK USING(BISBN) WHERE MID='choi';

insert into rsv(RSVNUM, RSVDATE, MID, BISBN) VALUES(RSVNUM_SEQ.NEXTVAL, SYSDATE, 'lee', 937488061);
insert into rsv(RSVNUM, RSVDATE, MID, BISBN) VALUES(RSVNUM_SEQ.NEXTVAL, SYSDATE, 'lee', 165210991);
insert into rsv(RSVNUM, RSVDATE, MID, BISBN) VALUES(RSVNUM_SEQ.NEXTVAL, SYSDATE, 'choi', 963193489);

/*
    반납...할 수 있다
*/
SELECT * FROM RETURN;
-- 대여 테이블에서 bISBN으로 검색하여 rnum 파라미터 받기
SELECT RNUM FROM RENT WHERE BISBN=937488061;
-- 반납 테이블에 insert
INSERT INTO RETURN(RNUM, RTDATE, BISBN) VALUES(2, SYSDATE, 937488061);
-- 대여 상태 false로 변경
UPDATE RENT SET RSTATUS=0 WHERE BISBN=937488061;
-- 예약된 도서가 있는지 확인 : 날짜 빠른 순서대로 rownum 지정해서 제일 빠른 번호 가져오기
SELECT MID FROM RSV WHERE BISBN=937488061 ORDER BY RSVDATE;
-- 예약된 도서가 있는 경우 그 아이디의 알림 코드를 20번으로 변경하기
UPDATE MEMBER SET NCODE=20 WHERE MID='lee';
-- 예약 도서를 바로 대출로 변경하기
INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID) VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, ?, ?)



-- 예약된 도서가 없는 경우 상태변수 증가시키기
SELECT RNUM FROM RENT WHERE BISBN=964212134;
INSERT INTO RETURN(RNUM, RTDATE, BISBN) VALUES(5, SYSDATE, 964212134);
SELECT MID FROM RSV WHERE BISBN=964212134;
UPDATE BOOK SET BSTATUS=1 WHERE BISBN=964212134;

SELECT * FROM BOOK WHERE BISBN=964212134;


update book set bstatus=1;
delete from rent where mid='choi';
delete from rsv where mid='choi';
delete from rsv where mid='lee';
delete from return where rnum=3;

select * from book; 932918372 932918389    이 책을 예약함-> 936434267 992969536
-- 필요한 상황
-- choi가 책을 대여하였고
INSERT INTO RENT(RNUM, RDATE, REXDATE, RSTATUS, BISBN, MID)
VALUES(RNUM_SEQ.NEXTVAL, SYSDATE, SYSDATE + 7, 1, 992969536, 'choi');

UPDATE BOOK SET BSTATUS = 0 WHERE BISBN=992969536;
-- lee가 일부 책을 예약한 상태
insert into rsv(RSVNUM, RSVDATE, MID, BISBN) VALUES(RSVNUM_SEQ.NEXTVAL, SYSDATE, 'lee', 992969536);

commit; 


-- 예약 내역 삭제
select * from rsv;
delete from rsv where bisbn=992969536;

/*
    예약하기
*/
SELECT * FROM RSV;
-- 도서의 대여여부 확인
SELECT * FROM BOOK ORDER BY SCODE;
SELECT BSTATUS FROM BOOK WHERE BISBN=970654409 AND BSTATUS=0;

INSERT INTO RSV(RSVNUM, RSVDATE, MID, BISBN) VALUES (RSVNUM_SEQ.NEXTVAL, SYSDATE, ?, ?);


UPDATE BOOK SET BSTATUS=1;
DELETE FROM RENT;
DELETE FROM RSV;
DELETE FROM RETURN;

COMMIT;

select sysdate from dual;

select * from member;
select * from book;

SELECT MABLE FROM MEMBER WHERE MID='sim' AND MSTATUS=1 AND SYSDATE < TO_CHAR(MABLE, 'yyyy-mm-dd');

select * from rent;