package com.example.CampMastery.Model;

import com.example.CampMastery.R;

import java.util.ArrayList;

public class BootcampData {
    private static String[] bootcampNames = {
            "Mobile App Development",
            "Cloud Computing Essentials",
            "Python for Data Analysis",
            "Internet of Things (IoT) Workshop",
            "Full Stack Web Development",
            "Machine Learning Algorithms",
            "DevOps Practices",
            "Game Development Fundamentals",
            "Blockchain Basics",
            "Artificial Intelligence Ethics",
            "Quantum Computing Intro",
            "Flutter App Development",
            "Natural Language Processing (NLP)",
            "AR/VR Development",
            "Kubernetes and Containerization",
            "Introduction to Programming",
            "Data Science Fundamentals",
            "Cybersecurity Essentials",
            "Web Development Crash Course",
            "UX/UI Design Workshop"
    };
    private static String[] bootcampDescriptions = {
            "Build mobile applications for iOS and Android platforms using Swift and Kotlin.",
            "Explore cloud computing platforms and services such as AWS and Azure.",
            "Learn Python programming specifically for data analysis and visualization.",
            "Dive into the world of IoT, connecting devices and creating smart applications.",
            "Master both frontend and backend development for building complete web applications.",
            "Explore advanced machine learning algorithms and their applications.",
            "Learn the principles of DevOps and tools for continuous integration and deployment.",
            "Create interactive games and explore game development concepts.",
            "Understand the fundamentals of blockchain technology and cryptocurrency.",
            "Discuss ethical considerations in artificial intelligence development and deployment.",
            "Explore the basics of quantum computing and its potential applications.",
            "Build cross-platform mobile apps using the Flutter framework.",
            "Dive into the field of NLP and learn how to process and analyze human language.",
            "Create immersive experiences with augmented and virtual reality technologies.",
            "Explore containerization with Docker and orchestration with Kubernetes.",
            "Learn the basics of programming with hands-on exercises and projects.",
            "Dive into the world of data science, covering statistical analysis and machine learning.",
            "Learn the fundamentals of cybersecurity, including network security and ethical hacking.",
            "Build responsive and dynamic websites from scratch using HTML, CSS, and JavaScript.",
            "Explore user experience and user interface design principles through practical design projects."
    };
    private static String[] startDate = {
            "2023-11-01",
            "2024-01-10",
            "2024-03-01",
            "2024-05-15",
            "2023-07-03",
            "2024-09-01",
            "2024-11-01",
            "2024-01-10",
            "2023-03-01",
            "2024-05-15",
            "2023-07-03",
            "2025-09-01",
            "2025-11-01",
            "2024-11-01",
            "2023-03-01",
            "2023-01-10",
            "2023-03-01",
            "2023-05-15",
            "2024-05-15",
            "2023-09-01"

    };

    private static String[] endDate = {
            "2023-12-06",
            "2024-02-15",
            "2024-04-05",
            "2024-06-20",
            "2023-08-08",
            "2024-10-06",
            "2024-12-06",
            "2024-02-15",
            "2023-04-05",
            "2024-06-20",
            "2023-08-08",
            "2025-10-06",
            "2025-12-06",
            "2024-12-06",
            "2023-04-05",
            "2023-02-15",
            "2023-04-05",
            "2023-06-20",
            "2024-06-20",
            "2023-10-06"
    };

    private static int[] coverImages = {
            R.drawable.cover_mobile,
            R.drawable.cover_cloud,
            R.drawable.cover_python,
            R.drawable.cover_iot,
            R.drawable.cover_web,
            R.drawable.cover_mesinler,
            R.drawable.cover_devops,
            R.drawable.cover_gamedev,
            R.drawable.cover_blockchain,
            R.drawable.cover_ai,
            R.drawable.cover_quantum,
            R.drawable.cover_flutter,
            R.drawable.cover_nlp,
            R.drawable.cover_ar,
            R.drawable.cover_kubernetes,
            R.drawable.cover_program,
            R.drawable.cover_datasc,
            R.drawable.cover_cyber,
            R.drawable.cover_web2,
            R.drawable.cover_uiux
    };

    public static ArrayList<Bootcamp> getListData() {
        ArrayList<Bootcamp> list = new ArrayList<>();
        for (int position = 0; position < bootcampNames.length; position++) {
            Bootcamp data = new Bootcamp();
            data.setTitle(bootcampNames[position]);
            data.setDeskripsi(bootcampDescriptions[position]);
            data.setStart(startDate[position]);
            data.setEnd(endDate[position]);
            data.setCover(coverImages[position]);

            list.add(data);
        }
        return list;
    };
    public static int[] getListDataCover() {
        int[] coverImageIds = new int[coverImages.length];
        for (int position = 0; position < coverImages.length; position++) {
            coverImageIds[position] = Integer.parseInt(String.valueOf(coverImages[position]));
        }
        return coverImageIds;
    }

}