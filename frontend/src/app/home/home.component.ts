import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StudentService } from '../service/student-service';
import { MatIconButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule,MatIconButton,MatIconModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  
})
export class HomeComponent {
  displayedColumns: string[] = ['name', 'email', 'phone', 'city', 'state', 'country', 'pincode','edit','delete'];
  dataSource = new MatTableDataSource<any>([]);
  totalLength = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private studentService:StudentService,private cdr: ChangeDetectorRef) {}

  ngAfterViewInit() {

    this.studentService.getStudents().subscribe(data => {
      console.log(data);
      
      this.dataSource.data = data;
      this.totalLength = data.length;
      this.dataSource.paginator = this.paginator;
      this.cdr.detectChanges();

  });
  }

  editStudent(student: any) {

  }

  deleteStudent(id: string) {
    const token = localStorage.getItem('jwtToken');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    this.studentService.deleteStudent(id).subscribe(response=>{
      console.log(response);
      
    });
    
  }}