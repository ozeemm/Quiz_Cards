--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-05-14 18:09:49

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

DROP DATABASE quiz_cards;
--
-- TOC entry 4873 (class 1262 OID 16612)
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
-- TOC entry 4874 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16613)
-- Name: cards; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.cards (
    id integer NOT NULL,
    front_text text NOT NULL,
    back_text text NOT NULL,
    packet_id integer NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 16618)
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
-- TOC entry 4875 (class 0 OID 0)
-- Dependencies: 216
-- Name: Cards_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Cards_id_seq" OWNED BY public.cards.id;


--
-- TOC entry 217 (class 1259 OID 16619)
-- Name: card_packets; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.card_packets (
    id integer NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    theme_id integer NOT NULL
);


--
-- TOC entry 218 (class 1259 OID 16624)
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
-- TOC entry 4876 (class 0 OID 0)
-- Dependencies: 218
-- Name: card_packets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.card_packets_id_seq OWNED BY public.card_packets.id;


--
-- TOC entry 222 (class 1259 OID 16654)
-- Name: card_packets_view; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.card_packets_view AS
 SELECT card_packets.id,
    card_packets.name,
    card_packets.description,
    card_packets.theme_id,
    unnamed_subquery.count AS cards_count
   FROM (public.card_packets
     JOIN ( SELECT cards.packet_id,
            count(cards.packet_id) AS count
           FROM public.cards
          GROUP BY cards.packet_id) unnamed_subquery ON ((card_packets.id = unnamed_subquery.packet_id)));


--
-- TOC entry 219 (class 1259 OID 16625)
-- Name: themes; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.themes (
    id integer NOT NULL,
    name text NOT NULL,
    description text NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 16630)
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
-- TOC entry 4877 (class 0 OID 0)
-- Dependencies: 220
-- Name: themes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.themes_id_seq OWNED BY public.themes.id;


--
-- TOC entry 221 (class 1259 OID 16650)
-- Name: themes_view; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW public.themes_view AS
 SELECT themes.id,
    themes.name,
    themes.description,
    unnamed_subquery.count AS packets_count
   FROM (public.themes
     JOIN ( SELECT card_packets.theme_id,
            count(*) AS count
           FROM public.card_packets
          GROUP BY card_packets.theme_id) unnamed_subquery ON ((themes.id = unnamed_subquery.theme_id)));


--
-- TOC entry 4707 (class 2604 OID 16631)
-- Name: card_packets id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card_packets ALTER COLUMN id SET DEFAULT nextval('public.card_packets_id_seq'::regclass);


--
-- TOC entry 4706 (class 2604 OID 16632)
-- Name: cards id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cards ALTER COLUMN id SET DEFAULT nextval('public."Cards_id_seq"'::regclass);


--
-- TOC entry 4708 (class 2604 OID 16633)
-- Name: themes id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.themes ALTER COLUMN id SET DEFAULT nextval('public.themes_id_seq'::regclass);


--
-- TOC entry 4864 (class 0 OID 16619)
-- Dependencies: 217
-- Data for Name: card_packets; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.card_packets VALUES (1, 'Право', 'Набор карточек про Право', 1);
INSERT INTO public.card_packets VALUES (3, 'Животные', 'Набор карточек про животых', 2);
INSERT INTO public.card_packets VALUES (2, 'Профессии', 'Набор карточек про профессии', 2);


--
-- TOC entry 4862 (class 0 OID 16613)
-- Dependencies: 215
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
-- TOC entry 4866 (class 0 OID 16625)
-- Dependencies: 219
-- Data for Name: themes; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.themes VALUES (1, 'Обществознание', 'Наборы про обществознание');
INSERT INTO public.themes VALUES (2, 'Английский', 'Наборы про английский');


--
-- TOC entry 4878 (class 0 OID 0)
-- Dependencies: 216
-- Name: Cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Cards_id_seq"', 24, true);


--
-- TOC entry 4879 (class 0 OID 0)
-- Dependencies: 218
-- Name: card_packets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.card_packets_id_seq', 16, true);


--
-- TOC entry 4880 (class 0 OID 0)
-- Dependencies: 220
-- Name: themes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.themes_id_seq', 8, true);


--
-- TOC entry 4710 (class 2606 OID 16635)
-- Name: cards Cards_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT "Cards_pkey" PRIMARY KEY (id);


--
-- TOC entry 4712 (class 2606 OID 16637)
-- Name: card_packets card_packets_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card_packets
    ADD CONSTRAINT card_packets_pkey PRIMARY KEY (id);


--
-- TOC entry 4714 (class 2606 OID 16639)
-- Name: themes themes_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.themes
    ADD CONSTRAINT themes_pkey PRIMARY KEY (id);


--
-- TOC entry 4716 (class 2606 OID 16640)
-- Name: card_packets card_packets_theme_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.card_packets
    ADD CONSTRAINT card_packets_theme_id_fkey FOREIGN KEY (theme_id) REFERENCES public.themes(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 4715 (class 2606 OID 16645)
-- Name: cards cards_packet_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_packet_id_fkey FOREIGN KEY (packet_id) REFERENCES public.card_packets(id) ON DELETE CASCADE NOT VALID;


-- Completed on 2024-05-14 18:09:50

--
-- PostgreSQL database dump complete
--

