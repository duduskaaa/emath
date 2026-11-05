CREATE TABLE user_roles (
    user_id UUID NOT NULL,
    role VARCHAR(10) NOT NULL
        CHECK ( role in ('STUDENT', 'TEACHER', 'PARENT', 'ADMIN') ),

    PRIMARY KEY (user_id, role),

    FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);