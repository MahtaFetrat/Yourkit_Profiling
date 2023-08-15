# Yourkit_Profiling

## تمرین
1. از پروژه ProfilingTest، عملیات Profiling را با استفاده از Yourkit بر روی کلاس JavaCup اجرا نموده و تابعی از پروژه که بیش‌ترین مصرف منابع را دارد شناسایی کنید و آن را در گزارش خود در فایل README توضیح دهید. سپس نحوه پیاده‌سازی آن تابع را به گونه‌ای تغییر دهید که مصرف منابع نسبت به قبل بهتر شود.

در ابتدا کلاس JavaCup را اجرا کردیم. اما به علت تعداد زیاد متغیرهایی که در تابع temp این کلاس به ArrayList افزوده می‌شود، این برنامه به خطای Out of Memory بر می‌خورد و حتی با افزایش حجم مجاز Heap در تنظیمات VM اینتلیجی، این خطا برطرف نشد. به منظور رفع این مشکل، بدون کم شدن از کلیت مسئله، range حلقه‌ی داخلی را به 5000 کاهش دادیم.

سپس با کلیک راست بر روی کلاس JavaCup، آن را به همراه Profiling برنامه‌ی Yourkit اجرا کردیم.

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/cf0feda9-8460-424d-9445-a2501b337664)

مقادیر ورودی برنامه را نیز برابر با اعداد 3 و 4 و 5 قرار دادیم که البته تاثیری در تابع temp ندارد اما مقدار چاپ‌شده‌ی تابع eval را YES می‌کند.

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/de0a99a1-ab85-45d8-9669-570ff47a5080)

خروجی‌های Profiling برنامه‌ی Yourkit برای این کلاس به شرح زیر می‌باشند.

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/71e6f57a-c8c2-4e17-9a3d-432fdaaf4afb)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/43910d3a-f69c-41f7-8fc9-ebde4d6b592e)

این تصویر نشان می‌دهد که قسمت عمده‌ی زمان CPU یعنی حدود 83 درصد، در تابع temp سپری شده‌است و این تابع درواقع bottlneck این کلاس می‌باشد.

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/d774ee49-d082-420c-a6ad-0a3f3098b591)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/4e3f1eda-1d3f-48a2-957a-66f25f37a609)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/c80333a5-6866-4afc-8ba2-e76a6ffdb9c2)

این تصویر نیز به خوبی نشان می‌دهد که عمده‌ی سربار اجرای تابع main کلاس JavaCup به فراخوانی تابع temp مربوط می‌شود.
حال قصد داریم که با ایجاد تغییر در نحوه‌ی پیاده‌سازی این تابع، سربار اجرایی آن را کاهش دهیم. با توجه به تصویر اول از خروجی‌های profiling می‌توان حدس زد که بخشی از این سربار به خاطر کوچک بودن ظرفیت اولیه‌ی ArrayList و نیاز به گسترش آن می‌باشد. بنابراین با تخصیص یک ظرفیت اولیه که به اندازه‌ی کافی بزرگ باشد، می‌توان این مسئله را برطرف نمود. کد مورد نظر به صورت زیر می‌شود:

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/b82690f7-af2b-40bb-b5e6-b5b35dbf3754)

خروجی profiling این نسخه‌ی ویرایش‌یافته به صورت زیر می‌باشد:

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/bd3ef1b8-f873-45dc-af6c-5f5dc6f9ad5b)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/308c1dcc-7ae4-4416-934d-8b4ba122baab)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/00298661-651c-49f4-a4c3-3e74c008b9ea)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/592b2608-750b-4822-9af9-e5a163347502)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/2017517f-2a14-45f3-9a32-4144da0127ca)

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/96a97bac-0612-4629-8e6a-e588417741a6)

همانطور که در این خروجی‌ها مشخص است، دیگر سربار اصلی کلاس، تابع temp نیست. بلکه فراخوانی‌های Scanner است که بخش اعظم زمان اجرا را به خود اختصاص می‌دهند.

2. قطعه کد دیگری (به جز الگوریتم‌های مرتب‌سازی) به زبان جاوا بنویسید و سپس عملیات Profiling را با استفاده از Yourkit بر روی آن اجرا و بخشی از کد که بیش‌ترین مصرف منابع را دارد شناسایی کنید. الگوریتم پیاده‌سازی آن قسمت را به گونه‌ای تغییر دهید که این مشکل برطرف شده و مصرف منابع نسبت به حالت فعلی بهتر شود. توجه داشته باشید کامنت کردن کد و یا صرفنظر کردن از اجرای آن و موارد مشابه قابل قبول نیست. همراه با ارسال پروژه لازم است گزارشی در مورد عملکرد کد و نحوه برطرف شدن مشکل در پیاده‌سازی جدید را ارائه نمایید. گزارش باید حاوی تصاویری از وضعیت مصرف کلیه منابع در حالت قبل و بعد از پیاده‌سازی جدید باشد.

برای نمایش یک نمونه‌ی دیگر از profiling، کدی نوشته‌ایم که در آن آرایه‌ای از موجودیت‌های Customer وجود دارد. سپس تعداد زیادی query بر اساس id این مشتریان، روی آن آرایه انجام خواهد شد. در مرحله‌ی اول، این query ها را به روش naive و با for روی آرایه و بررسی تک‌تک id ها انجام می‌دهیم. کد کلاسی که توصیف شد، به صورت زیر است:

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/59ca381c-7115-4b44-9c05-e33770a7d0b2)
![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/263a57ea-cfbc-4923-8e77-e7961f7cdeb9)

حال این کلاس را profile می‌کنیم:

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/3366608c-f304-4606-a3da-6b625dc969f2)
![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/61473e15-6247-4e0b-a050-04b432aaf9ce)

این دو تصویر از وضعیت مصرف CPU نشان می‌دهد که سربار اصلی اجرای این کلاس، مربوط به پاسخ دادن query ها است که به وضوح به صورت ناکارآمدی نیز پیاده‌سازی شده‌است. profiling وضعیت حافظه‌ی برنامه نیز نتایج زیر را به بار دارد.

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/065b5d70-3dad-4a38-8648-0a14df34e4bb)

همچنین یک خلاصه از منابع مصرفی این کلاس در تصویر زیر قابل مشاهده است.

![image](https://github.com/MahtaFetrat/Yourkit_Profiling/assets/62302965/ef3a3c74-dfd0-44ea-9e57-8244d6f3f8bb)


