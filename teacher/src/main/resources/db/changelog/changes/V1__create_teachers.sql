CREATE TABLE teachers (
    id UUID PRIMARY KEY ,
    first_name text NOT NULL,
    last_name text NOT NULL,
    email text NOT NULL UNIQUE ,
    phone_number text UNIQUE,
    hire_date date,
    subject text,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    active boolean DEFAULT true NOT NULL
);

