# MyRok: 대학생을 위한 통합 프로젝트 협업 플랫폼

## 목차
1. [시스템 개요](#시스템-개요)
2. [주요 기능](#주요-기능)
3. [시스템 구성도](#시스템-구성도)
4. [기술 스택](#기술-스택)
5. [시스템 설계](#시스템-설계)
6. [의미 있는 Business Logic/Algorithm](#의미-있는-business-logicalgorithm)
7. [팀 구성 및 역할](#팀-구성-및-역할)

## 시스템 개요

MyRok은 대학생들을 위한 올인원 프로젝트 협업 툴입니다. 디스코드, Google Docs, 노션 등 다양한 협업 툴의 핵심 기능들을 하나의 플랫폼에 통합하여 효율적인 프로젝트 관리 경험을 제공합니다.

### 특징 및 장점
- 직관적인 대시보드를 통한 프로젝트 진행 상황 파악
- 태그 기반 회의록 필터링 및 조회
- AI 기반 회의록 자동 요약 및 미니 챗봇 제공

## 주요 기능

1. 회원 관리
   - 소셜(Google) 회원가입 및 로그인/로그아웃
   - 회원 탈퇴

2. 프로젝트 관리
   - 프로젝트 생성, 참여, 나가기
   - 프로젝트 참여 코드 조회 및 참가

3. 회의록 관리
   - 회의록 생성, 삭제, 조회
   - 회의록 자동 요약
   - 태그 기반 회의록 필터링

4. 대시보드 관리
   - 태그 목록 및 퍼센트 그래프 조회
   - 최신 회의록 목록 조회

## 시스템 구성도

![image](https://github.com/user-attachments/assets/e73ad04b-aa5c-40d8-81a2-d4e03b60dcbe)


## 기술 스택

### Frontend
- React
- TypeScript
- Webpack
- Emotion (스타일링)
- Storybook (컴포넌트 테스트)
- CKEditor5 (리치 텍스트 에디터)
- TanStack Query (데이터 페칭 및 상태 관리)
- Axios (HTTP 클라이언트)

### Backend
- Spring Boot
- Spring Data JPA
- QueryDSL
- Spring Security
- JWT
- OAuth 2.0

### Database
- MySQL

### Testing
- JUnit
- Mockito
- MSW (Mock Service Worker)

## 시스템 설계

- [DB 설계](https://dbdiagram.io/d/666ed431a179551be6ff13a7)
- UI/UX 설계
- Domain 설계
- Controller 및 Service 클래스 설계
- Request 흐름 설계

## 의미 있는 Business Logic/Algorithm

### Frontend
1. ErrorBoundary 구현
2. 토큰 헤더 자동 주입 및 만료 시 재발급 요청 처리

### Backend
1. Spring Security를 활용한 다중 권한 처리
2. JWT 토큰 관리 및 재발급 로직
3. OpenAI API 비동기 호출 구현
4. Soft Delete 구현

## 팀 구성 및 역할

- 백승지 (팀장/백엔드): 회의록 요약, 프로젝트 관리, 인증/인가, 회의록 필터링
- 송지은 (프론트엔드): UI 설계 및 구현, 프론트엔드 전체 기능 구현
- 이가연 (백엔드): 회의록 CRUD, 대시보드 태그 관리
- 김도연 (백엔드): 프로젝트 정보 조회, 사용자 정보 조회

## 시연 영상
https://github.com/user-attachments/assets/2388db4c-86c1-4cb2-9ad6-e63f26b79453

## 화면
<img width="300" alt="Screenshot 2024-10-03 at 7 51 40 PM" src="https://github.com/user-attachments/assets/21f50090-ed1a-41d7-ae30-756e922fc78e">
<img width="300" alt="Screenshot 2024-10-03 at 7 51 48 PM" src="https://github.com/user-attachments/assets/2257db91-36da-4e28-8c0d-eed0d94d7331">
<img width="300" alt="Screenshot 2024-10-03 at 7 51 13 PM" src="https://github.com/user-attachments/assets/fc67fe15-60de-452e-a925-c42444372151">
<img width="300" alt="Screenshot 2024-10-03 at 7 51 54 PM" src="https://github.com/user-attachments/assets/136b6691-7025-48f5-ac37-ecf644f4efb7">
<img width="300" height="194" alt="Screenshot 2024-10-03 at 7 51 05 PM" src="https://github.com/user-attachments/assets/f57e7259-890b-40dd-a3d0-687376f88a65">
<img width="300" height="194" alt="Screenshot 2024-10-03 at 7 50 54 PM" src="https://github.com/user-attachments/assets/d496b48e-3bd3-4732-9f42-2354b305ea47">

# 프레젠테이션
[Myrok 최종 보고서](https://www.canva.com/design/DAGIA7CocuI/GUk_3Ln5KS9DwJsb_ISQjw/edit?utm_content=DAGIA7CocuI&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
