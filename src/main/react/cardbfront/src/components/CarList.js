import React, { Component } from "react";
import { SERVER_URL } from "../constants.js";
import ReactTable from "react-table-6";
import "react-table-6/react-table.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
class CarList extends Component {
  constructor(props) {
    super(props);
    this.state = { cars: [] };
  }
  onDelClick = link => {
   if (window.confirm('are you ok to delete ?'))
   {
    fetch(link, { method: "DELETE" })
      .then(res => {
        toast.success("car deleted", { position: toast.POSITION.BOTTOM_LEFT });
        this.fetchCar();
      })
      .catch(err => {
        toast.error("error when deleting", {
          position: toast.POSITION.BOTTOM_LEFT
        });

        console.error(err);
      });
  };
}
  fetchCar = () => {
    fetch(SERVER_URL + "/api/cars")
      .then(response => response.json())
      .then(responseData => {
        this.setState({ cars: responseData._embedded.cars });
      })
      .catch(err => console.error(err));
  };
  componentDidMount() {
    this.fetchCar();
  }
  render() {
    const columns = [
      {
        Header: "Brand",
        accessor: "brand"
      },
      {
        Header: "Model",
        accessor: "model"
      },
      {
        Header: "Color",
        accessor: "color"
      },
      {
        Header: "Year",
        accessor: "year"
      },
      {
        Header: "Price Euro",
        accessor: "price"
      },
      {
        id: "delButton",
        filterable: false,
        sortable: false,
        width: 100,
        accessor: "_links.self.href",
        Cell: ({ value }) => (
          <button
            onClick={() => {
              this.onDelClick(value);
            }}
          >
            Delete
          </button>
        )
      }
    ];

    return (
      <div className="App">
        <ReactTable
          data={this.state.cars}
          columns={columns}
          filterable={true}
        />
        <ToastContainer autoClose={3000} />
      </div>
    );
  }
}
export default CarList;