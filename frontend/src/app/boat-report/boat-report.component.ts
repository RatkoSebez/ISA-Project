import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Chart, ChartConfiguration, LineController, LineElement, PointElement, LinearScale, Title, registerables} from 'chart.js'
import { BoatInfoPageComponent } from '../boat-info-page/boat-info-page.component';

@Component({
  selector: 'app-boat-report',
  templateUrl: './boat-report.component.html',
  styleUrls: ['./boat-report.component.css']
})
export class BoatReportComponent implements OnInit {

  canvas: any;
  ctx: any;
  myMonth: any;
  myChartWeek: any
  myChartIncome: any

  startDate: String | any;
  endDate: String | any;
  startDateIncome: String | any;
  endDateIncome: String | any;
  myVar : Record<string, number> = {

 }
 totalPerWeek = 0;
 rating = 0.0

  constructor(private http: HttpClient, public component: BoatInfoPageComponent) {Chart.register(...registerables) }

  ngOnInit(): void {
    this.rating = this.component.boat.rating
    this.http.post<any>('api/boat/numResYearBoat', this.component.id).subscribe(response => {
      console.log(response)
      let keys = Object.keys(response)
      let values = Object.values(response)
      this.canvas = document.getElementById('myChartYear');
      this.ctx = this.canvas.getContext('2d');
      let myChartYear = new Chart(this.ctx, {
        type: 'bar',
        data: {
          labels: keys,
          datasets: [{
            label: '# of Reservations',
            data: values,
            backgroundColor: [
              'rgba(255, 99, 132, 0.5)',
              'rgba(54, 162, 235, 0.5)',
              'rgba(54, 100, 235, 0.5)'
            ],
            borderWidth: 0.5,

          }],
        },

        options: {
          responsive: false
        }
      });
      }
    );
    this.monthrep("2022");
  }

  monthrep(year:any){
    const postData={
      id: this.component.id,
      year: year
    }
    this.http.post<any>('api/boat/numResMontBoat', postData ).subscribe((response:any) => {
      this.canvas = document.getElementById('myMonth');
      this.ctx = this.canvas.getContext('2d');
      this.myMonth = new Chart(this.ctx, {
        type: 'bar',
        data: {
          labels: ["January", "February", "March", "April", "May", "Jun", "July", "August", "September", "October", "November", "December"],
          datasets: [{
            label: '# of Reservations',
            data: [response["JANUARY"], response["FEBRUARY"], response["MARCH"], response["APRIL"], response["MAY"], response["JUN"], response["JULY"], response["AUGUST"], response["SEPTEMBER"], response["OCTOBER"], response["NOVEMBER"], response["DECEMBER"]],
            backgroundColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(255, 145, 86, 1)',
              'rgba(255, 104, 86, 1)',
              'rgba(255, 125, 86, 1)',
              'rgba(255, 23, 86, 1)',
              'rgba(255, 206, 75, 1)',
              'rgba(255, 206, 124, 1)',
              'rgba(47, 206, 124, 1)',
              'rgba(125, 206, 124, 1)',
              'rgba(10, 206, 124, 1)',


            ],
            borderWidth: 1
          }]
        },
        options: {
          responsive: false,
        }
      });
    });
  }

  selectYear($event: any) {
    console.log($event.target.value)
    this.myMonth.destroy();
    this.monthrep($event.target.value);

  }


  selectDays() {
    var start = formatDate(this.startDate,'yyyy-MM-dd','en_US');
    var end  = formatDate(this.endDate,'yyyy-MM-dd','en_US');
    start+= " 15:00"
    end+= " 13:00"

    const data = {
      id: this.component.id,
      startDate: start,
      endDate: end
    }

    this.http.post<any>('api/boat/numResSpecWeekBoat', data ).subscribe((response:any) => {
      this.http.post<any>('api/boat/numResWeekBoat', data ).subscribe((response:any) => {
        this.totalPerWeek = response
      })

      if (this.myChartWeek !== undefined) {
        this.myChartWeek.destroy();
      }
      this.reportPerWeek(response)

    });
  }

  reportPerWeek(data : any) {
    this.myVar = data;
    let first = Object.keys(data)
    let values = Object.values(data)
    this.canvas = document.getElementById('myChartWeek');
    this.ctx = this.canvas.getContext('2d');
    this.myChartWeek = new Chart(this.ctx, {
      type: 'bar',
      data: {
        labels: first,
        datasets: [{
          label: '# of Reservations',
          data: values,
          backgroundColor:
            'rgba(255, 99, 132, 1)',

          borderWidth: 1
        }]
      },
      options: {
        responsive: false,
      }
    });
  }

  selectDaysIncome() {

    if (this.startDateIncome || this.endDateIncome) {

      var start = formatDate(this.startDateIncome,'yyyy-MM-dd','en_US');
      var end  = formatDate(this.endDateIncome,'yyyy-MM-dd','en_US');

      start += " 15:00"
      end += " 13:00"

      var dto = {
        "id": this.component.id,
        "startDate": start,
        "endDate": end
      }

      this.http.post<any>('api/boat/incomeSpecWeekBoat', dto ).subscribe((response:any) => {
        if (this.myChartIncome !== undefined) {
          this.myChartIncome.destroy();
        }
        this.reportIncome(response)
      })
    }
  }

  reportIncome(response: any) {
    this.myVar = response;
    let first = Object.keys(response)
    let values = Object.values(response)
    this.canvas = document.getElementById('myChartIncome');
    this.ctx = this.canvas.getContext('2d');
    this.myChartIncome = new Chart(this.ctx, {
      type: 'bar',
      data: {
        labels: first,
        datasets: [{
          label: '# of Reservations',
          data: values,
          backgroundColor:
            'rgba(255, 99, 132, 1)',

          borderWidth: 1
        }]
      },
      options: {
        responsive: false,
      }
    });
  }
}
