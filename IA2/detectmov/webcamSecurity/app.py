import cv2
import pygame

pygame.init()
pygame.mixer.init()

cam = cv2.VideoCapture(0)
alert_played = False

while cam.isOpened():
    success, frame1 = cam.read()
    success, frame2 = cam.read()
    if success:
        diff = cv2.absdiff(frame1, frame2)
        gray = cv2.cvtColor(diff, cv2.COLOR_BGR2GRAY)
        blur = cv2.GaussianBlur(gray, (5, 5), 0)
        _, thresh = cv2.threshold(blur, 50, 255, cv2.THRESH_BINARY)
        dilate = cv2.dilate(thresh, None, iterations=4)

        contours, _ = cv2.findContours(dilate, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
        movement_detected = False
        for c in contours:
            if cv2.contourArea(c) < 1000:
                continue
            x, y, w, h = cv2.boundingRect(c)
            cv2.rectangle(frame1, (x, y), (x+w, y+h), (0, 0, 255), 2)
            movement_detected = True

        if movement_detected and not alert_played:
            sound = pygame.mixer.Sound('siren-alert-96052.mp3')
            sound.play()
            alert_played = True
        elif not movement_detected:
            alert_played = False

        cv2.imshow('Live 1', frame1)

        if cv2.waitKey(1) == ord('q'):
            break

cam.release()
cv2.destroyAllWindows()