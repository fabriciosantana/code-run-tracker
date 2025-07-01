CREATE TABLE executions (
    id SERIAL PRIMARY KEY,
    institution VARCHAR(150) NOT NULL,
    course VARCHAR(150) NOT NULL,
    subject VARCHAR(150) NOT NULL,
    semester VARCHAR(20) NOT NULL,
    student_id VARCHAR(50) NOT NULL,
    task_code VARCHAR(100) NOT NULL,
    executed_at TIMESTAMP NOT NULL,
    local_user VARCHAR(100),
    ip_address VARCHAR(100)
);