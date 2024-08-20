 Anonymous Chat App within Radius of 20 to 30 m (August - Present)
Description:
- Developed an Android application enabling anonymous chat between users within a 20 to 30-meter radius using Bluetooth and location services.
- Enhanced user anonymity by assigning random names using the TMDB API and safeguarding users against cyberbullying with TensorFlow Lite.

Skills and Technologies:
- Retrofit & TMDB API: Integrated Retrofit to fetch random names from the TMDB API, adding a fun and dynamic element to the anonymous chat experience.
- Google Location Services: Utilized Google Location Services to accurately determine the user’s proximity and manage chat availability within the specified radius.
- TensorFlow Lite: Implemented TensorFlow Lite for real-time detection and prevention of cyberbullying within chat messages, promoting a safer chat environment.
- Material UI: Designed the app using Material UI components, ensuring a modern, visually appealing, and intuitive user interface.
- Firebase: Used Firebase to manage real-time message exchanges while preserving user anonymity and ensuring data security.
- MVVM Architecture: Applied MVVM to maintain a clean separation of concerns, allowing for easier maintenance and scalability.
- Foreground Service: Implemented a foreground service to continuously track user location and maintain the chat functionality even when the app is in the background.
- Notifications: Integrated push notifications to alert users when they receive new messages or when someone new enters their chat radius.

Challenges and Solutions:
- Accurate Proximity Detection: Achieved precise detection of nearby users by combining Bluetooth scanning with Google Location Services, ensuring the chat feature was both reliable and responsive.
- User Privacy and Anonymity: Enhanced user privacy by automatically assigning anonymous names through the TMDB API and ensuring all communications were encrypted.
- Cyberbullying Prevention: Addressed the challenge of ensuring a safe chat environment by using TensorFlow Lite to analyze and filter out potentially harmful content in real time.
- Performance Optimization: Managed the app's performance by efficiently handling location updates and notifications through a foreground service, minimizing battery drain while ensuring seamless user experience.
