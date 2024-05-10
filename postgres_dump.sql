--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-05-11 04:37:27

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
-- TOC entry 4863 (class 1262 OID 16498)
-- Name: quiz_cards; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE quiz_cards WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


\connect quiz_cards

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16501)
-- Name: cards; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.cards (
    id integer NOT NULL,
    front_text text NOT NULL,
    back_text text NOT NULL,
    packet_id integer NOT NULL
);


--
-- TOC entry 215 (class 1259 OID 16500)
-- Name: Cards_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Cards_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 215
-- Name: Cards_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Cards_id_seq" OWNED BY public.cards.id;


--
-- TOC entry 218 (class 1259 OID 16510)
-- Name: card_packets; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.card_packets (
    id integer NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    theme_id integer NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 16509)
-- Name: card_packets_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.card_packets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 217
-- Name: card_packets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.card_packets_id_seq OWNED BY public.card_packets.id;


--
-- TOC entry 220 (class 1259 OID 16519)
-- Name: themes; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.themes (
    id integer NOT NULL,
    name text NOT NULL,
    description text NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 16518)
-- Name: themes_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.themes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4867 (class 0 OID 0)
-- Dependencies: 219
-- Name: themes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.themes_id_seq OWNED BY public.themes.id;


--
-- TOC entry 4699 (class 2604 OID 16513)
-- Name: card_packets id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card_packets ALTER COLUMN id SET DEFAULT nextval('public.card_packets_id_seq'::regclass);


--
-- TOC entry 4698 (class 2604 OID 16504)
-- Name: cards id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cards ALTER COLUMN id SET DEFAULT nextval('public."Cards_id_seq"'::regclass);


--
-- TOC entry 4700 (class 2604 OID 16522)
-- Name: themes id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.themes ALTER COLUMN id SET DEFAULT nextval('public.themes_id_seq'::regclass);


--
-- TOC entry 4855 (class 0 OID 16510)
-- Dependencies: 218
-- Data for Name: card_packets; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.card_packets VALUES (1, 'Право', 'Набор карточек про Право', 1);
INSERT INTO public.card_packets VALUES (3, 'Животные', 'Набор карточек про животых', 2);
INSERT INTO public.card_packets VALUES (2, 'Профессии', 'Набор карточек про профессии', 2);


--
-- TOC entry 4853 (class 0 OID 16501)
-- Dependencies: 216
-- Data for Name: cards; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.cards VALUES (1, 'Cat', 'Кошка', 3);
INSERT INTO public.cards VALUES (2, 'Dog', 'Собака', 3);
INSERT INTO public.cards VALUES (3, 'Rabbit', 'Кролик', 3);
INSERT INTO public.cards VALUES (5, 'Driver', 'Водитель', 2);
INSERT INTO public.cards VALUES (6, 'Baker', 'Пекарь', 2);
INSERT INTO public.cards VALUES (9, 'Суд', 'Орган государства, осуществляющий правосудие', 1);
INSERT INTO public.cards VALUES (7, 'Частное право', 'Право, регулирующее отношения между индивидуумами, коллективами', 1);
INSERT INTO public.cards VALUES (8, 'Завещание', 'Односторонняя сделка, распоряжение своим имуществом на случай смерти', 1);
INSERT INTO public.cards VALUES (4, 'Teacher', 'Учитель', 2);


--
-- TOC entry 4857 (class 0 OID 16519)
-- Dependencies: 220
-- Data for Name: themes; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.themes VALUES (1, 'Обществознание', 'Наборы про обществознание');
INSERT INTO public.themes VALUES (2, 'Английский', 'Наборы про английский');


--
-- TOC entry 4868 (class 0 OID 0)
-- Dependencies: 215
-- Name: Cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Cards_id_seq"', 24, true);


--
-- TOC entry 4869 (class 0 OID 0)
-- Dependencies: 217
-- Name: card_packets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.card_packets_id_seq', 15, true);


--
-- TOC entry 4870 (class 0 OID 0)
-- Dependencies: 219
-- Name: themes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.themes_id_seq', 8, true);


--
-- TOC entry 4702 (class 2606 OID 16508)
-- Name: cards Cards_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT "Cards_pkey" PRIMARY KEY (id);


--
-- TOC entry 4704 (class 2606 OID 16517)
-- Name: card_packets card_packets_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card_packets
    ADD CONSTRAINT card_packets_pkey PRIMARY KEY (id);


--
-- TOC entry 4706 (class 2606 OID 16526)
-- Name: themes themes_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.themes
    ADD CONSTRAINT themes_pkey PRIMARY KEY (id);


--
-- TOC entry 4708 (class 2606 OID 16596)
-- Name: card_packets card_packets_theme_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card_packets
    ADD CONSTRAINT card_packets_theme_id_fkey FOREIGN KEY (theme_id) REFERENCES public.themes(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4707 (class 2606 OID 16601)
-- Name: cards cards_packet_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_packet_id_fkey FOREIGN KEY (packet_id) REFERENCES public.card_packets(id) ON DELETE CASCADE NOT VALID;


-- Completed on 2024-05-11 04:37:28

--
-- PostgreSQL database dump complete
--

