import React from 'react';
import ReactDOM from 'react-dom/client';
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import AnswerForm from '../Components/AnswerForm';

const fetchQuestion = async(id) => {
    return await (await fetch(`/questions/${id}`)).json();
}

const DetailedQuestions = () => {

    const {id} = useParams();

    const [question, setQuestion] = useState(null);
    const [loading, setLodaing] = useState(true);

    useEffect(() => {
        setLodaing(true);

        const getQuestion = async () => {

            try {
                const data = await fetchQuestion(id);
                console.log(data)
                setQuestion(data);
                setLodaing(false);
            } catch (err) {
                console.log(err);
            }
        }
        getQuestion();
    }, [])

    if(loading) return <div>Loading</div>

    return (<div>
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
                <div>
                    {answer.description}
                </div>
            </div>)}
        </div>

        <AnswerForm />
    </div>);
}
 
export default DetailedQuestions;