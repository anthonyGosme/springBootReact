import React, { Component } from "react";
import { SERVER_URL } from "../constants.js";
import ReactTable from "react-table-6";
import "react-table-6/react-table.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AddCar from "./AddCar";
import EditCar from "./EditCar";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import { CSVLink } from "react-csv";

class CarList extends Component {
  constructor(props) {
    super(props);
    this.state = { cars: [] };
  }

  addCar(car) {
    const token = sessionStorage.getItem("jwt");
    fetch(SERVER_URL + "/api/cars", {
      method: "POST",
      headers: {
        Authorization: token,
        "Content-Type": "application/json"
      },
      body: JSON.stringify(car)
    })
      .then(res => {
        this.fetchCars();
      })
      .catch(err => console.error(err));
  }

  updateCar(car, link) {
    const token = sessionStorage.getItem("jwt");
    fetch(link, {
      method: "PUT",
      headers: {
        Authorization: token,
        "Content-Type": "application/json"
      },
      body: JSON.stringify(car)
    })
      .then(res => {
        toast.success("car edited", {
          position: toast.POSITION.BOTTOM_LEFT
        });
        this.fetchCars();
      })
      .catch(err => {
        toast.error("error when editing car", {
          position: toast.POSITION.BOTTOM_LEFT
        });
        console.error(err);
      });
  }

  onDelClick = link => {
    const token = sessionStorage.getItem("jwt");
    if (window.confirm("are you ok to delete ?")) {
      fetch(link, {
        method: "DELETE",
        headers: {
          Authorization: token
        }
      })
        .then(res => {
          toast.success("car deleted", {
            position: toast.POSITION.BOTTOM_LEFT
          });
          this.fetchCar();
        })
        .catch(err => {
          toast.error("error when deleting", {
            position: toast.POSITION.BOTTOM_LEFT
          });
          console.error(err);
        });
    }
  };
  fetchCar = () => {
    const token = sessionStorage.getItem("jwt");
    fetch(SERVER_URL + "/api/cars", { headers: { Authorization: token } })
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
          <Button
            size="small"
            color="secondary"
            onClick={() => {
              this.onDelClick(value);
            }}
          >
            Delete
          </Button>
        )
      },
      {
        id: "editButton",
        filterable: false,
        sortable: false,
        width: 100,
        accessor: "_links.self.href",
        Cell: ({ value, row }) => (
          <EditCar
            car={row}
            link={value}
            updateCar={this.updateCar}
            fetchCars={this.fetchCar}
          />
        )
      }
    ];

    return (
      <div className="App">
        <Grid container>
          <Grid item>
            <AddCar addCar={this.addCar} fetchCars={this.fetchCar} />
          </Grid>
          <Grid item style={{ padding: 15 }}>
            <CSVLink data={this.state.cars}>Export CSV</CSVLink>
          </Grid>
        </Grid>
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
