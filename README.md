## 실제 실행화면
![image](https://github.com/junni01kim/DevideImageGame/assets/127941871/b6ffc8bb-dfd2-45cd-bf5b-6061fd5789d5)

## 분할된 이미지를 원본대로 정렬시키는 게임
 해당 게임은 이미지를 분할하여 맞추는 게임이다. 우측 상단에는 진행도(완성)가 나타나며 마우스를 이용하여 분할된 사진을 이용할 수 있다.

## 게임 기능
1) 자동분할기능: 게임시작 시 기본 이미지(DevideImageGame.png)를 프로그램 내부에서 3x3으로 분할하여 랜덤 배치된다.
2) 이미지탐색기: 옵션바(File)에서 파일탐색기를 열고 원하는 이미지를 지목하여 분할시킬 수 있다.  
   ### 예시  
   ![image](https://github.com/junni01kim/DevideImageGame/assets/127941871/53645783-2add-4546-8760-9e65ff88725f)  
   ![image](https://github.com/junni01kim/DevideImageGame/assets/127941871/5d5e626f-673d-4083-b343-bee4ffefcdca)
3) ?x?분할기: 옵션바(File)에서 파일탐색기를 열고 원하는 행열을 정하고 분할시킬 수 있다.  
   ### 예시  
   ![image](https://github.com/junni01kim/DevideImageGame/assets/127941871/d843bd1c-0a73-49dd-9cca-6f8f24531c25)  
   ![image](https://github.com/junni01kim/DevideImageGame/assets/127941871/95ad54fc-c1f7-4397-b581-f539fba3c59a)
4) 진행상황저장: 분할된 이미지를 그대로 저장한다.  
     - 분할된 이미지의 정답은 파일 이름에 저장되며, SaveImage?x?0000..로 저장된다.
     - 이미지는 (./)에 저장된다.

## 파일 구조
  

## 프로그램 설명
- ```GamePanel```은 3x3 사이즈의 9개의 ```DevideGamePanel``` 객체를 가지고 있다.
- ```GamePanel```에서는 제공한 이미지 파일(900x900 사이즈 확정)을 가지고 9개로 분리하며, 각 이미지는 ```DevideGamePanel```에 ```Grapic으```로 저장된다.
- 시작 시 나누어진 ```DevideGamePanel```은 순서대로 ```myImageIndex```를 갖고 시작과 동시에 index를 재배치한다.
 - ```GamePanel```에서 ```MouseAdapter```를 이용하여 pressed와 released된 위치의 위치의 ```DevideGamePanel```의 ```Image``` 객체를 교환한다.  
    - 이때는 고유 위치 index를 함께 교환한다.(이것으로 이미지 정보를 구분)
- ```ScorePanel```에서는 ```DevideGamePanel```의 index와 myImageIndex의 값이 같은지를 구분하여 카운트하고 퍼센트를 구분한다.
