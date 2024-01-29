## 실제 실행화면
  ![image](https://github.com/junni01kim/DevideImageGame/assets/127941871/b277ef88-7b98-4901-bdb7-05c1a3d726eb)

## 분할된 이미지를 원본대로 정렬시키는 게임

## 파일 구조

## 프로그램 설명
- ```GamePanel```은 3x3 사이즈의 9개의 ```DevideGamePanel``` 객체를 가지고 있다.
- ```GamePanel```에서는 제공한 이미지 파일(900x900 사이즈 확정)을 가지고 9개로 분리하며, 각 이미지는 ```DevideGamePanel```에 ```Grapic으```로 저장된다.
- 시작 시 나누어진 ```DevideGamePanel```은 순서대로 ```myImageIndex```를 갖고 시작과 동시에 index를 재배치한다.
 - ```GamePanel```에서 ```MouseAdapter```를 이용하여 pressed와 released된 위치의 위치의 ```DevideGamePanel```의 ```Image``` 객체를 교환한다.  
    - 이때는 고유 위치 index를 함께 교환한다.(이것으로 이미지 정보를 구분)
- ```ScorePanel```에서는 ```DevideGamePanel```의 index와 myImageIndex의 값이 같은지를 구분하여 카운트하고 퍼센트를 구분한다.
