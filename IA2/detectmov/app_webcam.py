import cv2 
frameWidth = 640
frameHeigh = 480
cap = cv2.VideoCapture('media/test_video.mp4')
cap.set(3,frameWidth)
cap.set(4,frameHeigh)
while True:
    success, img = cap.read()
    cv2.imshow('live',img)
    img_gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    if cv2.waitKey(10) == ord('q'): #cv2.waitKey(1)& 0xFF == ord('q')
        break 