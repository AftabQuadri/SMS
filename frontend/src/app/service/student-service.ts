import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {provideHttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'   
})
export class StudentService {
  private baseUrl = 'http://localhost:9999';  

  constructor(private http: HttpClient) {}

  // Method to get the list of students
  getStudents(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/get-all`);  
  }

  insertNewStudent(student:any):Observable<any>{
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<any>(`${this.baseUrl}+"/add-student"`,student,{headers});
  }
  editStudent(student:any):Observable<any>{
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<any>(`${this.baseUrl}/update`,student,{headers});
  }
  deleteStudent(id:string):Observable<any>{
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete<any>(`${this.baseUrl}/delete/${id}`,{headers});
  }
}

