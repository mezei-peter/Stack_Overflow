import React from "react";
import ReactDOM from "react-dom/client";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import AnswerForm from "../Components/AnswerForm";
import "./detailedQuestions.css";

const fetchQuestion = async (id) => {
  return await (await fetch(`/questions/${id}`)).json();
};

const postAnswer = async (question, answer, answer_poster_id) => {
  return await fetch(`/answers/`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      question_id: `${question.questionId}`,
      description: `${answer}`,
      answer_poster_id: `${answer_poster_id}`,
    }),
  });
};

const deleteAnswer = async (answerId) => {
  return await fetch(`/answers/${answerId}`, {
    method: "DELETE",
  });
};

const DetailedQuestions = () => {
  const { id } = useParams();

  const [question, setQuestion] = useState(null);
  const [userId, setUserId] = useState(-1);
  const [loading, setLodaing] = useState(true);

  const onSave = async (answer) => {
    try {
      const sessionId = localStorage.getItem("sessionId");
      const userId = await (
        await fetch(`/user/userIdFromSessionId/${sessionId}`)
      ).text();
      const res = await postAnswer(question, answer, userId);
      window.location.reload();
    } catch (err) {
      console.log(err);
    }
  };

  const handleAnswerDelete = async (answerId) => {
    const res = await deleteAnswer(answerId);
    console.log(res);
    window.location.reload();
  };

  useEffect(() => {
    setLodaing(true);

    const getQuestion = async () => {
      try {
        const data = await fetchQuestion(id);
        setQuestion(data);
        setLodaing(false);
        const sessionId = localStorage.getItem("sessionId");
        const userId = await (
          await fetch(`/user/userIdFromSessionId/${sessionId}`)
        ).text();
        setUserId(userId);
        console.log(userId);
      } catch (err) {
        console.log(err);
      }
    };
    getQuestion();
  }, []);

  if (loading) return <div>Loading</div>;

  return (
    <div id="detailedContainer">
      <div className="DetailedQuestionContainer">
        <div>Posted By: {question.username}</div>
        <div>Created: {question.created}</div>
        <div className="TitleContainer">{question.title}</div>
        <div className="DescriptionCOntainer">{question.description}</div>
      </div>

      <div>Answers: </div>
      <div className="answers">
        {question.answers.map((answer) => (
          <div className="AnswerContaier" key={answer.answerId}>
            <div className="AnswerDescriptionContainer">{answer.description}</div>
            <div className="AnswerDetailsContainer">
              <div>{answer.username}</div>
              {userId.toString() === answer.answerPosterUserId.toString() ? (
                <div>
                  <button onClick={() => handleAnswerDelete(answer.answerId)}>
                    Delete answer
                  </button>
                </div>
              ) : null}
            </div>
          </div>
        ))}
      </div>

      {userId > -1 ? <AnswerForm onSave={onSave} /> : null}
    </div>
  );
};

export default DetailedQuestions;
