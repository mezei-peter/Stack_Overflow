import React from 'react';
import ReactDOM from 'react-dom/client';
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import AnswerForm from '../Components/AnswerForm';
import './detailedQuestions.css';

const fetchQuestion = async(id) => {
    return await (await fetch(`/questions/${id}`)).json();
}

const postAnswer = async (question, answer, answer_poster_id) => {
    await fetch(`/answers/`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "question_id" : `${question.questionId}`,
            "description" : `${answer}`,
            "answer_poster_id" : `${answer_poster_id}`
        })
    })
}

const DetailedQuestions = () => {

    const {id} = useParams();

    const [question, setQuestion] = useState(null);
    const [loading, setLodaing] = useState(true);

    const onSave = async (answer) => {
        try {
            const sessionId = localStorage.getItem("sessionId");
            const userId = await (await fetch(`/user/userIdFromSessionId/${sessionId}`)).text();
            const res = await postAnswer(question, answer, userId);
            window.location.reload();
            console.log(res)
        } catch (err) {
            console.log(err);
        }

    } 

    useEffect(() => {
        setLodaing(true);

        const getQuestion = async () => {

            try {
                const data = await fetchQuestion(id);
                setQuestion(data);
                setLodaing(false);
            } catch (err) {
                console.log(err);
            }
        }
        getQuestion();
    }, [])

    if(loading) return <div>Loading</div>

    return (<div id='detailedContainer'>
        <div>
            Username: {question.username}
        </div>
        <div>
            Created: {question.created}
        </div>
        <div>
            Title: {question.title}
        </div>
        <div>
            Description: {question.description}
        </div>
        <div>Answers: </div>
        <div>
            {question.answers.map(answer => <div key={answer.answerId}>
                <div id='answerContainer'>
                    {answer.description}
                </div>
            </div>)}
        </div>

        <AnswerForm onSave={onSave}/>
    </div>);
}
 
export default DetailedQuestions;