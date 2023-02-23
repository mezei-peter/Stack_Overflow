import React from "react";
import ReactDOM from "react-dom/client";
import { Link } from "react-router-dom";
import './home.css';

import { useState, useEffect } from "react";

const fetchQuestions = async (signal) => {
  return await (await fetch("/questions/all", { signal })).json();
};

// const deleteDivison = async(id) => {
//   return await (await fetch(`/api/divisons/${id}`, {
//     method: "DELETE",
//   })).json()
// }

const Home = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  //   const handleDelete = async (id) => {
  //     try {
  //       const res = await deleteDivison(id)
  //       console.log(res)
  //     } catch (err) {
  //       console.log(err)
  //     }
  //   }

  useEffect(() => {
    const controller = new AbortController();
    const getAllQuestions = async () => {
      try {
        const data = await fetchQuestions(controller.signal);
        console.log(data);
        setData(data);
        setLoading(false);
      } catch (err) {
        console.log(err);
      }
    };
    getAllQuestions();
    return () => controller.abort();
  }, []);

  if (loading) return <div>Loading</div>;

  return (
    <div>
      <h2 id='titleHome'>Welcome to our homepage!</h2>
      <div className="questions">
        {data.map((question, index) => (
          <div key={question.questionId}>
            <div>Index: {index + 1}</div>
            <div>Title: {question.title}</div>
            <div> Number of answers: {question.numberOfAnswers}</div>
            <div> Posted by: {question.username}</div>
            <Link to={`/questions/${question.questionId}`}>
              <button variant="outlined">View</button>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
