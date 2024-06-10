--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: gender; Type: TYPE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TYPE public.gender AS ENUM (
    'FEMALE',
    'MALE'
);


ALTER TYPE public.gender OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: mood; Type: TYPE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TYPE public.mood AS ENUM (
    'ANGRY',
    'ANXIOUS',
    'DIASPPOINTED',
    'HAPPY',
    'NERVOUS',
    'SAD'
);


ALTER TYPE public.mood OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: order_status; Type: TYPE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TYPE public.order_status AS ENUM (
    'WAITING',
    'FAILED',
    'DONE'
);


ALTER TYPE public.order_status OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: status; Type: TYPE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TYPE public.status AS ENUM (
    'ON GOING',
    'FINISHED'
);


ALTER TYPE public.status OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: usertype; Type: TYPE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TYPE public.usertype AS ENUM (
    'PATIENT',
    'DOCTOR'
);


ALTER TYPE public.usertype OWNER TO "AnxietyBuddy_DB_owner";

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: consule_table; Type: TABLE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TABLE public.consule_table (
    consuleid uuid DEFAULT gen_random_uuid() NOT NULL,
    doctorid uuid DEFAULT gen_random_uuid() NOT NULL,
    doctorname character varying(100) NOT NULL,
    patientid uuid DEFAULT gen_random_uuid() NOT NULL,
    patientname character varying(100) NOT NULL,
    date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    message text[] NOT NULL,
    status public.status NOT NULL
);


ALTER TABLE public.consule_table OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: doctors_table; Type: TABLE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TABLE public.doctors_table (
    workingid uuid DEFAULT gen_random_uuid() NOT NULL,
    doctorid uuid DEFAULT gen_random_uuid() NOT NULL,
    doctorname character varying(100) NOT NULL,
    experience character varying[] NOT NULL,
    speciality character varying(100) NOT NULL,
    workingplace character varying(100) NOT NULL
);


ALTER TABLE public.doctors_table OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: medicine_table; Type: TABLE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TABLE public.medicine_table (
    medicinecode character varying(6) NOT NULL,
    medicinename character varying(50) NOT NULL,
    price numeric(10,2) NOT NULL,
    description text NOT NULL,
    dosage character varying(50) NOT NULL,
    quantity integer NOT NULL,
    type character varying(10),
    medicine_picture text
);


ALTER TABLE public.medicine_table OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: moodjournal_table; Type: TABLE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TABLE public.moodjournal_table (
    journalid uuid DEFAULT gen_random_uuid() NOT NULL,
    userid uuid DEFAULT gen_random_uuid() NOT NULL,
    currentmood public.mood NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    "time" time without time zone DEFAULT CURRENT_TIME NOT NULL,
    text text NOT NULL
);


ALTER TABLE public.moodjournal_table OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: order_table; Type: TABLE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TABLE public.order_table (
    orderid uuid DEFAULT gen_random_uuid() NOT NULL,
    recipeid uuid,
    patientid uuid NOT NULL,
    patientname character varying(100) NOT NULL,
    medicinecode character varying(6) NOT NULL,
    orderdate date NOT NULL,
    quantity integer NOT NULL,
    totalprice numeric(10,2) NOT NULL,
    status public.order_status NOT NULL,
    medicinename character varying(100)
);


ALTER TABLE public.order_table OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: recipe_table; Type: TABLE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TABLE public.recipe_table (
    recipeid uuid DEFAULT gen_random_uuid() NOT NULL,
    doctorid uuid,
    doctorname character varying(100) NOT NULL,
    patientid uuid NOT NULL,
    patientname character varying(100) NOT NULL,
    "time" timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    medicinecodes character varying(100),
    medicinenames character varying(50)[]
);


ALTER TABLE public.recipe_table OWNER TO "AnxietyBuddy_DB_owner";

--
-- Name: user_table; Type: TABLE; Schema: public; Owner: AnxietyBuddy_DB_owner
--

CREATE TABLE public.user_table (
    userid uuid DEFAULT gen_random_uuid() NOT NULL,
    username character varying(100) NOT NULL,
    useremail character varying(100) NOT NULL,
    gender public.gender NOT NULL,
    userpassword character varying(100) NOT NULL,
    usertype public.usertype NOT NULL,
    userbalance numeric(10,2) DEFAULT 0,
    profile_picture text,
    CONSTRAINT password_min_length_check CHECK ((length((userpassword)::text) >= 8))
);


ALTER TABLE public.user_table OWNER TO "AnxietyBuddy_DB_owner";

--
-- Data for Name: consule_table; Type: TABLE DATA; Schema: public; Owner: AnxietyBuddy_DB_owner
--

COPY public.consule_table (consuleid, doctorid, doctorname, patientid, patientname, date, message, status) FROM stdin;
732aa922-a193-4ea3-9a12-9978fe201f5b	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 07:04:25.571585	{Hola}	ON GOING
148f5f05-d7c5-4948-a46b-f46df9264a1d	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 07:04:27.491996	{Hola}	ON GOING
83a028cf-193a-4f58-9c47-8b75a9238ec5	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 07:04:57.547758	{Hola}	ON GOING
31dcc829-cff3-4dd3-9d6f-2c893d7ad1db	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 07:05:15.416517	{===START===,Test}	FINISHED
6922d056-58e1-41ba-8f61-c41c521fd6fd	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 07:26:23.215691	{===START===}	FINISHED
a1478b95-575c-483e-968f-992059e2ea5b	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 07:22:11.576258	{===START===,"Jadi gini","Jadi gini","Jadi gini","Jadi gini","Kepiting kikir"}	FINISHED
16e3d77a-850b-4f6e-8c56-63110514b1a9	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 15:16:11.377315	{===START===,"Jadi gini",Awikwok}	ON GOING
\.


--
-- Data for Name: doctors_table; Type: TABLE DATA; Schema: public; Owner: AnxietyBuddy_DB_owner
--

COPY public.doctors_table (workingid, doctorid, doctorname, experience, speciality, workingplace) FROM stdin;
347b0531-8714-4698-8f1a-0ed1e2727408	0c910746-6976-4093-8052-b64fba454779	Star	{Pet,surgeon,undefined,Nurse,Nurse,surgeon}	Surgeon	Hospital N
ca61f919-83ae-490c-84dd-9e462a0c29ab	c2412799-4912-4c29-a549-8b01a93476df	Autumn	{Pet,Surgeon}	Surgeon	Hospital N
\.


--
-- Data for Name: medicine_table; Type: TABLE DATA; Schema: public; Owner: AnxietyBuddy_DB_owner
--

COPY public.medicine_table (medicinecode, medicinename, price, description, dosage, quantity, type, medicine_picture) FROM stdin;
ABC222	Oskadon	3000.00	Obat Sakit Kepala	2 x 1 hari	10	VITAMIN	\N
ABC12	Test	13000.00	Test	2 x 1 Seharu	-4	\N	\N
ABC13	Testt	13000.00	Test	2 x 1 Seharu	200	SKIN	\N
PC11	Paracetamol	5000.00	Obat demam	2 x 1 hari	10	FEVER	\N
\.


--
-- Data for Name: moodjournal_table; Type: TABLE DATA; Schema: public; Owner: AnxietyBuddy_DB_owner
--

COPY public.moodjournal_table (journalid, userid, currentmood, date, "time", text) FROM stdin;
1f81bdf1-225d-4050-a86a-63ef49ce24e2	2db7a675-65a8-4921-b770-8b3b368edb0c	ANGRY	2024-06-10	08:37:10.651777	Pusing
cfd6464e-c18c-4ab5-8d5c-99d10bbf1093	2db7a675-65a8-4921-b770-8b3b368edb0c	ANGRY	2024-06-10	08:37:25.650169	Pusing
3f95ff9b-c257-4e0d-aebd-4c9efa8007be	2db7a675-65a8-4921-b770-8b3b368edb0c	HAPPY	2024-06-10	08:45:16.146961	Hello
66668515-39ca-4d88-9b8d-dd96e4cfc8cc	c2412799-4912-4c29-a549-8b01a93476df	ANXIOUS	2024-06-10	15:15:56.27489	Hello
\.


--
-- Data for Name: order_table; Type: TABLE DATA; Schema: public; Owner: AnxietyBuddy_DB_owner
--

COPY public.order_table (orderid, recipeid, patientid, patientname, medicinecode, orderdate, quantity, totalprice, status, medicinename) FROM stdin;
7a4dc75a-2f10-498d-80a9-68229f97c083	\N	2a7d9f78-23b6-417b-bee5-fff6232b2e18	Winter	ABC12	2024-06-10	2	26000.00	WAITING	Test
e005c290-6753-4f88-906c-f216953c6914	\N	2a7d9f78-23b6-417b-bee5-fff6232b2e18	Winter	ABC12	2024-06-10	2	26000.00	WAITING	Test
fb47fb2b-f42c-4bfd-a538-35316bbe3694	\N	2a7d9f78-23b6-417b-bee5-fff6232b2e18	Winter	ABC12	2024-06-10	2	26000.00	DONE	Test
065f473b-e818-4145-8aca-3b62616771e1	\N	2a7d9f78-23b6-417b-bee5-fff6232b2e18	Winter	ABC12	2024-06-10	2	26000.00	DONE	Test
\.


--
-- Data for Name: recipe_table; Type: TABLE DATA; Schema: public; Owner: AnxietyBuddy_DB_owner
--

COPY public.recipe_table (recipeid, doctorid, doctorname, patientid, patientname, "time", medicinecodes, medicinenames) FROM stdin;
bbce4fd3-963a-4c7b-b085-54e1ddc98550	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 08:15:27.257827	{ABC12}	{TEst}
950267e5-223f-4654-bdf4-7ae1bee00f07	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 08:15:35.206278	{ABC12}	{TEst}
096cb627-5777-4f91-bae8-2d3b4902f786	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 08:17:10.570167	{"ABC12, AB"}	{TEst}
f44c32b3-f34b-4352-acff-29b301c66918	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 08:20:03.55488	{"ABC12, AB"}	{TEst}
a892730b-0187-4593-a085-52a4cda030fe	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 08:20:04.734701	{ABC12,AB}	{TEst}
c36cde24-ea49-4990-8d72-a9178b80dc31	0c910746-6976-4093-8052-b64fba454779	Star	2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	2024-06-10 08:30:23.793931	{"AB12, AB13"}	{"Test, Test"}
\.


--
-- Data for Name: user_table; Type: TABLE DATA; Schema: public; Owner: AnxietyBuddy_DB_owner
--

COPY public.user_table (userid, username, useremail, gender, userpassword, usertype, userbalance, profile_picture) FROM stdin;
2db7a675-65a8-4921-b770-8b3b368edb0c	Spongebob	spongebob@gmail.com	MALE	krabbypatty	PATIENT	\N	\N
562d5429-0af8-47f4-8fcc-53eba643cab8	Patrick	patrick@gmail.com	MALE	krabbypatty	DOCTOR	\N	\N
2a7d9f78-23b6-417b-bee5-fff6232b2e18	Winter	winter@gmail.com	FEMALE	12345678910	PATIENT	3500.00	\N
aba84f9b-449c-40f9-a5d5-2feab5397f55	Kingkong	fancypants@gmail.com	FEMALE	abcde1234	PATIENT	0.00	\N
d69f3ba9-8409-4302-973d-59613fb38d88	wawan	Wawan@gmail.com	FEMALE	wawan12345	DOCTOR	0.00	\N
0c910746-6976-4093-8052-b64fba454779	Star	star@gmail.com	MALE	akupatrcik	DOCTOR	3000.00	\N
e440ff8b-8984-4b5f-b331-986cd1372f9b	Susisusi	Susi@gmail.com	FEMALE	susi1234	PATIENT	0.00	\N
e87b371c-8a19-474f-9add-9749dba8c6fd	Mark Hoffman	Hoffman@gmail.com	MALE	hoffman1234	DOCTOR	0.00	\N
9bfa6f74-c05c-4d50-ab5b-ff495d46c508	Mark Hoffmann	Hoffmann@gmail.com	MALE	hoffman1234	DOCTOR	0.00	\N
fed461cb-86a6-4c91-90b8-c638f358a1ba	Saya upin	Upin@gmail.com	MALE	ulinipin	PATIENT	0.00	\N
5c427427-461e-4ccc-b3e1-0d209b8b34cf	Babi	babi@gmail.com	MALE	babi1234	DOCTOR	0.00	\N
d0f2f70d-e50b-4686-a6c2-34456f8fa6a3	Wianter	wianter@gmail.com	FEMALE	akusquidward	PATIENT	0.00	\N
c2412799-4912-4c29-a549-8b01a93476df	Autumn	autumn@gmail.com	FEMALE	akusquidward	PATIENT	0.00	\N
\.


--
-- Name: consule_table consule_table_consuleid_key; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.consule_table
    ADD CONSTRAINT consule_table_consuleid_key UNIQUE (consuleid);


--
-- Name: doctors_table doctors_table_doctorid_key; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.doctors_table
    ADD CONSTRAINT doctors_table_doctorid_key UNIQUE (doctorid);


--
-- Name: medicine_table medicine_table_medicinecode_key; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.medicine_table
    ADD CONSTRAINT medicine_table_medicinecode_key UNIQUE (medicinecode);


--
-- Name: medicine_table medicine_table_medicinename_key; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.medicine_table
    ADD CONSTRAINT medicine_table_medicinename_key UNIQUE (medicinename);


--
-- Name: moodjournal_table moodjournal_table_pkey; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.moodjournal_table
    ADD CONSTRAINT moodjournal_table_pkey PRIMARY KEY (journalid);


--
-- Name: order_table order_table_pkey; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT order_table_pkey PRIMARY KEY (orderid);


--
-- Name: order_table order_table_recipeid_key; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT order_table_recipeid_key UNIQUE (recipeid);


--
-- Name: recipe_table recipe_table_pkey; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.recipe_table
    ADD CONSTRAINT recipe_table_pkey PRIMARY KEY (recipeid);


--
-- Name: user_table unique_userid; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT unique_userid UNIQUE (userid);


--
-- Name: user_table user_table_useremail_key; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_useremail_key UNIQUE (useremail);


--
-- Name: user_table user_table_username_key; Type: CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_username_key UNIQUE (username);


--
-- Name: consule_table consule_table_doctorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.consule_table
    ADD CONSTRAINT consule_table_doctorid_fkey FOREIGN KEY (doctorid) REFERENCES public.doctors_table(doctorid);


--
-- Name: consule_table consule_table_patientid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.consule_table
    ADD CONSTRAINT consule_table_patientid_fkey FOREIGN KEY (patientid) REFERENCES public.user_table(userid);


--
-- Name: doctors_table doctors_table_doctorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.doctors_table
    ADD CONSTRAINT doctors_table_doctorid_fkey FOREIGN KEY (doctorid) REFERENCES public.user_table(userid);


--
-- Name: doctors_table doctors_table_doctorname_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.doctors_table
    ADD CONSTRAINT doctors_table_doctorname_fkey FOREIGN KEY (doctorname) REFERENCES public.user_table(username);


--
-- Name: moodjournal_table moodjournal_table_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.moodjournal_table
    ADD CONSTRAINT moodjournal_table_userid_fkey FOREIGN KEY (userid) REFERENCES public.user_table(userid);


--
-- Name: order_table order_table_medicinecode_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT order_table_medicinecode_fkey FOREIGN KEY (medicinecode) REFERENCES public.medicine_table(medicinecode);


--
-- Name: order_table order_table_patientid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT order_table_patientid_fkey FOREIGN KEY (patientid) REFERENCES public.user_table(userid);


--
-- Name: order_table order_table_patientname_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT order_table_patientname_fkey FOREIGN KEY (patientname) REFERENCES public.user_table(username);


--
-- Name: order_table order_table_recipeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT order_table_recipeid_fkey FOREIGN KEY (recipeid) REFERENCES public.recipe_table(recipeid);


--
-- Name: recipe_table recipe_table_doctorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.recipe_table
    ADD CONSTRAINT recipe_table_doctorid_fkey FOREIGN KEY (doctorid) REFERENCES public.doctors_table(doctorid);


--
-- Name: recipe_table recipe_table_patientid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: AnxietyBuddy_DB_owner
--

ALTER TABLE ONLY public.recipe_table
    ADD CONSTRAINT recipe_table_patientid_fkey FOREIGN KEY (patientid) REFERENCES public.user_table(userid);


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: cloud_admin
--

ALTER DEFAULT PRIVILEGES FOR ROLE cloud_admin IN SCHEMA public GRANT ALL ON SEQUENCES TO neon_superuser WITH GRANT OPTION;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: cloud_admin
--

ALTER DEFAULT PRIVILEGES FOR ROLE cloud_admin IN SCHEMA public GRANT ALL ON TABLES TO neon_superuser WITH GRANT OPTION;


--
-- PostgreSQL database dump complete
--

