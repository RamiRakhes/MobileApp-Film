import cv2
import numpy as np
import face_recognition
import threading

# Import signatures
original_signature = np.load('../Signatures.npy')
# Get features only
signature_only = original_signature[:, 0:-1].astype('float')
# Get names only
names = original_signature[:, -1]

# Tolerance parameter
TOLERANCE = 0.6

class VideoStream(threading.Thread):
    def __init__(self, src=0):
        threading.Thread.__init__(self)
        self.capture = cv2.VideoCapture(src)
        self.ret = False
        self.frame = None
        self.running = True

    def run(self):
        while self.running:
            self.ret, self.frame = self.capture.read()
            if not self.ret:
                break

    def get_frame(self):
        return self.ret, self.frame

    def stop(self):
        self.running = False
        self.capture.release()

# Start the video stream
video_stream = VideoStream()
video_stream.start()

try:
    while True:
        success, img = video_stream.get_frame()
        if success:
            imgS = cv2.resize(img, (0, 0), None, 0.25, 0.25)
            imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)
            # Find face location from the web
            facesCurrent = face_recognition.face_locations(imgS)
            # Extract features
            encodesCurrent = face_recognition.face_encodings(imgS, facesCurrent)
            # Compare live faces to signature faces
            for encodeFace, faceLoc in zip(encodesCurrent, facesCurrent):
                matches = face_recognition.compare_faces(signature_only, encodeFace, tolerance=TOLERANCE)
                faceDistance = face_recognition.face_distance(signature_only, encodeFace)
                minIndex = np.argmin(faceDistance)
                if matches[minIndex]:
                    name = names[minIndex]
                else:
                    name = 'Unknown'
                
                # Scale back up face locations since the frame we detected in was scaled to 1/4 size
                y1, x2, y2, x1 = faceLoc
                y1, x2, y2, x1 = y1*4, x2*4, y2*4, x1*4
                
                # Draw rectangle around the face
                color = (255, 0, 0) if matches[minIndex] else (0, 255, 0)
                cv2.rectangle(img, (x1, y1), (x2, y2), color, 2)
                cv2.rectangle(img, (x1, y2-25), (x2, y2), (0, 255, 0) if matches[minIndex] else (0, 0, 255), cv2.FILLED)
                cv2.putText(img, name, (x1 + 10, y2 - 6), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 255, 255), 2)
            
            cv2.imshow('Live Face Recognition', img)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break
finally:
    video_stream.stop()
    cv2.destroyAllWindows()
