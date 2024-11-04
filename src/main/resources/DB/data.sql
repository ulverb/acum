-- Insert sample data
INSERT INTO students (first_name, last_name, email, date_of_birth, field_of_study) VALUES
('John', 'Doe', 'john.doe@example.com', '2000-05-14', 'Computer Science'),
('Jane', 'Smith', 'jane.smith@example.com', '1999-08-22', 'Mathematics'),
('Sam', 'Green', 'sam.green@example.com', '2001-11-30', 'Physics'),
('Lucy', 'Brown', 'lucy.brown@example.com', '1998-07-19', 'Biology'),
('Mike', 'White', 'mike.white@example.com', '1997-04-11', 'Engineering');

INSERT INTO courses (name, description, hours, max_students) VALUES
('Introduction to Computer Science', 'Basic concepts of computer science', 60, 30),
('Calculus 1', 'Introduction to calculus', 45, 25),
('Physics 101', 'Fundamental physics concepts', 50, 30),
('Biology Basics', 'Introduction to biology', 40, 20),
('Engineering Principles', 'Foundational engineering concepts', 60, 35);

INSERT INTO lecturers (first_name, last_name, email, field_of_study) VALUES
('Dr. Alice', 'Johnson', 'alice.johnson@example.com', 'Computer Science'),
('Dr. Bob', 'Williams', 'bob.williams@example.com', 'Mathematics'),
('Dr. Charlie', 'Brown', 'charlie.brown@example.com', 'Physics'),
('Dr. Emma', 'Jones', 'emma.jones@example.com', 'Biology'),
('Dr. Henry', 'Miller', 'henry.miller@example.com', 'Engineering');

INSERT INTO student_courses (student_id, course_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(4, 1);

INSERT INTO lecturer_courses (lecturer_id, course_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(4, 1);
