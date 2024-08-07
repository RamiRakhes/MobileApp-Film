import cv2

# Chemin vers le fichier vidéo
video_path = 'media/test_video.mp4'

# Capture de la vidéo
cap = cv2.VideoCapture(video_path)

# Définition de la largeur et de la hauteur des frames
frame_width = 640
frame_height = 480
cap.set(cv2.CAP_PROP_FRAME_WIDTH, frame_width)
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, frame_height)

# Boucle de lecture des frames
while True:
    # Lecture d'une frame
    success, frame = cap.read()
    if not success:
        break
    
    # Conversion de la frame en niveaux de gris
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    # Affichage de la frame en couleur et en niveaux de gris
    cv2.imshow('Live', frame)
    cv2.imshow('Gray', gray)

    # Sortie de la boucle si la touche 'q' est pressée
    if cv2.waitKey(10) == ord('q'):
        break

# Libération des ressources
cap.release()
cv2.destroyAllWindows()
